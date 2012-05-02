/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package yfsc.controllers;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import yfsc.businesslogic.BackupService;

@Controller
@RequestMapping("/adminServices")
@PreAuthorize("hasRole('Admin')")
public class AdminServicesController {
	
	@Inject BackupService backupService;
	
	@RequestMapping("/backup")
	public @ResponseBody String backup(HttpServletResponse response) {
		response.addHeader("Content-Disposition", "attachment;filename=backup.sql");
		return backupService.getBackupScript();
	}
}
