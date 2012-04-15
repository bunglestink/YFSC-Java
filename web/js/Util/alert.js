/*
 * alert.js 
 * 
 * depends on: jQuery, jQuery UI
 */
;
window.UTIL = window.UTIL || {};

(function ($, util) {
	
	var alert = function (message) {
		$('<div>')
			.html(message)
			.appendTo('body')
			.dialog({
				modal: true,
				autoOpen: true,
				title: 'Message',
				resizable: false,
				buttons: {
					'Ok': function () {
						$(this).dialog('close');
					}
				},
				close: function () {
					$(this).remove();
				}
			});
	};
	
	
	
	// public interface
	util.alert = alert;
	
}(jQuery, window.UTIL));