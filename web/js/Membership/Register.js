;
MODEL = (function () {
	
	var ViewModelFactory = {
		
		Create: function (currentSessionsUrl, registrationBaseUrl, getRegistrationCostUrl, callback) {
			var viewModel = {
		        // determines wether the data is finished loading
		        loadingComplete: function () {
		            return this.Registration && this.CurrentSessions;
		        },
		
		        // finish creation if loading complete, then call callback
		        dataBind: function () {
		            if (this.loadingComplete()) {
		                // bind selected session
		                this.selectedSession = ko.observable(this.CurrentSessions[0]);
		                this.TotalCost = ko.observable(0);
		                
		                // build view methods
		                this.addSkater = function () {
					        this.Registration.Skaters.push(new Skater(this.Registration));
					        $('.datefield').datepicker();
					    };
					    this.copySkater = function () {
					        var skater = new Skater(this.Registration);
					        skater.FirstName(this.Registration.FirstName());
					        skater.LastName(this.Registration.LastName());
					        skater.MiddleName(this.Registration.MiddleName());
					        this.Registration.Skaters.push(skater);
					        $('.datefield').datepicker();
					    };
					    this.addSession = function (skater) {
						    viewModel.selectedSkater = skater;
						    $('#session-selector-dialog').dialog('open');
						};
						
						this.refreshTotalCost = function () {
							var data = viewModel.Registration.toJsObject();
							
							viewModel.TotalCost('calculating...');
							
							$.ajax({
								url: getRegistrationCostUrl,
								type: 'post',
								contentType: 'application/json',
								data: JSON.stringify(data),
								success: function (totalCost) {
									viewModel.TotalCost(totalCost);
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
		        
		        viewModel.CurrentSessions = ko.observableArray(data);
		        for (sessionKey=0; sessionKey < viewModel.CurrentSessions().length; sessionKey++) {
		        	session = viewModel.CurrentSessions()[sessionKey];
		        	var date = UTIL.fixJsonDate(session.StartDate);
		        	session.StartDate = (date.getMonth()+1) + '/' + date.getDate() + '/' + date.getFullYear();
		        }
		        
		        viewModel.dataBind();
		    });
		
		    $.getJSON(registrationBaseUrl, function (data) {
		        // assign viewModel from server, then try to dataBind
		        viewModel.Registration = new Registration(data);
		        viewModel.dataBind();
		    });
		}
	};
	
	
	
	/** Registration **/
	function Registration(data) {
		var self = $.extend(this, ko.mapping.fromJS(data));
        self.skaterCount = ko.computed(function () { return self.Skaters().length; });
        return self;
	}
	
	Registration.prototype.toJsObject = function () {
		if (!(this instanceof Registration)) {
			return;
		}
	
        var result = {
        	ID: this.ID(),
			FirstName: this.FirstName(),
        	MiddleName: this.MiddleName(),
        	LastName: this.LastName(),
        	Street: this.Street(),
        	City: this.City(),
        	State: this.State(),
			Zip: this.Zip(),
			HomePhone: this.HomePhone(),
			WorkPhone: this.WorkPhone(),
			Email: this.Email(),
			YaleAffiliation: this.YaleAffiliation(),
			NameOfAffiliatedPerson: this.NameOfAffiliatedPerson(),
			YaleAffiliationType: this.YaleAffiliationType(),
			Department: this.Department(),
			School: this.School(),
			Year: this.Year(),
			Skaters: []
		};
		
        for (var skaterKey in this.Skaters()) {
            var skater = this.Skaters()[skaterKey],
            	sessions = [];
            for (var sessionKey in skater.Sessions()) {
            	var session = skater.Sessions()[sessionKey];
            	sessions.push({
            		ID: session.ID,
            		TotalCost: session.TotalCost
            	});
            }
            
            result.Skaters.push({
            	FirstName: skater.FirstName(),
            	LastName: skater.LastName(),
            	Sessions: sessions
            });
        }
        
        return result;
    };
	/** End Registration **/
	
	
	/** Skater **/
	function Skater(registration) {
	    this.registration = registration;
	    
	    this.ID = ko.observable(0);
	    this.FirstName = ko.observable('');
	    this.MiddleName = ko.observable('');
	    this.LastName = ko.observable('');
	    this.Sex = ko.observable('');
	    this.USCitizen = ko.observable(true);
	    this.BirthDate = ko.observable('');
	    this.NewRegistrant = ko.observable(true);
	    this.Level = ko.observable('');
	    this.Sessions = ko.observableArray([]);
	    
	    this.fullName = ko.computed(function () {
	        if (!this.FirstName && !this.LastName) {
	        	return '(no name entered)';
	        }
	        
	        return (this.FirstName() || '') + ' ' + (this.LastName() || '');
	    }, this);
	}
	
	Skater.prototype.remove = function () {
	    this.registration.Skaters.remove(this);
	};
	/** End Skater **/
	
	
	/** SkaterSession - represents a skater session **/
	function SkaterSession(session, skater) {
	    this.ID = session.ID;
	    this.Name = session.Name;
	    this.StartDate = session.StartDate;
	    this.DayOfWeek = session.DayOfWeek;
	    this.StartTime = session.StartTime;
	    this.EndTime = session.EndTime;
	    this.TotalCost = session.TotalCost;
	    this.WeeksDuration = session.WeeksDuration;
	    this.Description = session.Description;
	    
	    this.Skater = skater;
	};
	SkaterSession.prototype.remove = function() {
	    this.Skater.Sessions.remove(this);
	};
	/** End SkaterSession **/
	
	
	
	/* public interface */
	return {
		Skater: Skater,
		SkaterSession: SkaterSession,
		ViewModelFactory: ViewModelFactory
	};
}());