;
MODEL = (function () {
	
	var ViewModelFactory = {
		
		Create: function (currentSessionsUrl, registrationBaseUrl, getRegistrationCostUrl, callback) {
			var viewModel = {
		        // determines wether the data is finished loading
		        loadingComplete: function () {
		            return this.registration && this.currentSessions;
		        },
		
		        // finish creation if loading complete, then call callback
		        dataBind: function () {
		            if (this.loadingComplete()) {
		                // bind selected session
		                this.selectedSession = ko.observable(this.currentSessions[0]);
		                this.totalCost = ko.observable(0);
						this.sexOptions = ['F', 'M'];
						this.skatingLevels = ['Regular', 'Basic'];
		                
		                // build view methods
		                this.addSkater = function () {
					        this.registration.skaters.push(new Skater(this.registration));
					        $('.datefield').datepicker();
					    };
					    this.copySkater = function () {
					        var skater = new Skater(this.registration);
					        skater.firstName(this.registration.firstName());
					        skater.lastName(this.registration.lastName());
					        skater.middleName(this.registration.middleName());
					        this.registration.skaters.push(skater);
					        $('.datefield').datepicker();
					    };
					    this.addSession = function (skater) {
						    viewModel.selectedSkater = skater;
						    $('#session-selector-dialog').dialog('open');
						};
						
						this.refreshTotalCost = function () {
							var data = viewModel.registration.toJsObject();
							
							viewModel.totalCost('calculating...');
							
							$.ajax({
								url: getRegistrationCostUrl,
								type: 'post',
								contentType: 'application/json',
								data: JSON.stringify(data),
								success: function (totalCost) {
									viewModel.totalCost(totalCost);
									return true;
								},
								error: function (jqXhr, statusText) {
									UTIL.alert('Error calculating cost: <br /><br />', statusText);
									return true;
								}
							});
							return true;
						};
		
		                callback(viewModel);
		            }
		        }
		    };
		    
		    // get the viewModel, load into view
		    $.getJSON(currentSessionsUrl, function (data) {
		        var sessionKey, session;
		        
		        viewModel.currentSessions = ko.observableArray(data);
		        for (sessionKey=0; sessionKey < viewModel.currentSessions().length; sessionKey++) {
		        	session = viewModel.currentSessions()[sessionKey];
		        	var date = UTIL.fixJsonDate(session.startDate);
		        	session.startDate = (date.getMonth()+1) + '/' + date.getDate() + '/' + date.getFullYear();
		        }
		        
		        viewModel.dataBind();
		    });
		
		    $.getJSON(registrationBaseUrl, function (data) {
		        // assign viewModel from server, then try to dataBind
		        viewModel.registration = new Registration(data);
		        viewModel.dataBind();
		    });
		}
	};
	
	
	
	/** Registration **/
	function Registration(data) {
		var self = $.extend(this, ko.mapping.fromJS(data));
        self.skaterCount = ko.computed(function () { return self.skaters().length; });
        return self;
	}
	
	Registration.prototype.toJsObject = function () {
		if (!(this instanceof Registration)) {
			return;
		}
	
        var result = {
        	id: this.id(),
			firstName: this.firstName(),
        	middleName: this.middleName(),
        	lastName: this.lastName(),
        	street: this.street(),
        	city: this.city(),
        	state: this.state(),
			zip: this.zip(),
			homePhone: this.homePhone(),
			workPhone: this.workPhone(),
			email: this.email(),
			yaleAffiliation: this.yaleAffiliation(),
			nameOfAffiliatedPerson: this.nameOfAffiliatedPerson(),
			yaleAffiliationType: this.yaleAffiliationType(),
			department: this.department(),
			school: this.school(),
			year: this.year(),
			skaters: []
		};
		
        for (var skaterKey in this.skaters()) {
            var skater = this.skaters()[skaterKey],
            	sessions = [];
            for (var sessionKey in skater.sessions()) {
            	var session = skater.sessions()[sessionKey];
            	sessions.push({
            		id: session.id,
            		totalCost: session.totalCost
            	});
            }
            
			var dob = new Date(skater.birthDate());
			var dobString = dob.getFullYear() + '-' + (dob.getMonth() + 1) + '-' + dob.getDate();
			
            result.skaters.push({
            	firstName: skater.firstName(),
            	lastName: skater.lastName(),
				sex: skater.sex(),
				usCitizen: skater.usCitizen(),
				birthDate: dobString,
				newRegistrant: skater.newRegistrant(),
				level: skater.level(),
				
            	sessions: sessions
            });
        }
        
        return result;
    };
	/** End Registration **/
	
	
	/** Skater **/
	function Skater(registration) {
	    this.registration = registration;
	    
	    this.id = ko.observable(0);
	    this.firstName = ko.observable('');
	    this.middleName = ko.observable('');
	    this.lastName = ko.observable('');
	    this.sex = ko.observable('');
	    this.usCitizen = ko.observable(true);
	    this.birthDate = ko.observable('');
	    this.newRegistrant = ko.observable(true);
	    this.level = ko.observable('');
	    this.sessions = ko.observableArray([]);
	    
	    this.fullName = ko.computed(function () {
	        if (!this.firstName && !this.lastName) {
	        	return '(no name entered)';
	        }
	        
	        return (this.firstName() || '') + ' ' + (this.lastName() || '');
	    }, this);
	}
	
	Skater.prototype.remove = function () {
	    this.registration.skaters.remove(this);
	};
	/** End Skater **/
	
	
	/** SkaterSession - represents a skater session **/
	function SkaterSession(session, skater) {
	    this.id = session.id;
	    this.name = session.name;
	    this.startDate = session.startDate;
	    this.dayOfWeek = session.dayOfWeek;
	    this.startTime = session.startTime;
	    this.endTime = session.endTime;
	    this.totalCost = session.totalCost;
	    this.weeksDuration = session.weeksDuration;
	    this.description = session.description;
	    
	    this.skater = skater;
	};
	SkaterSession.prototype.remove = function() {
	    this.skater.sessions.remove(this);
	};
	/** End SkaterSession **/
	
	
	
	/* public interface */
	return {
		Skater: Skater,
		SkaterSession: SkaterSession,
		ViewModelFactory: ViewModelFactory
	};
}());