;

window.UTIL = window.UTIL || {};

(function ($, util) {
	var fixJsonDate = function (date) {
			
			return new Date(Date(date));
		};
		
	/* public interface */
	util.fixJsonDate = fixJsonDate;
}(jQuery, window.UTIL));
