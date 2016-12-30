/*
*
*    Copyright © 2015-2016 Tübitak ULAKBIM
*
*    This file is part of Lider Ahenk.
*
*    Lider Ahenk is free software: you can redistribute it and/or modify
*    it under the terms of the GNU General Public License as published by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    Lider Ahenk is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with Lider Ahenk.  If not, see <http://www.gnu.org/licenses/>.
*/
package tr.org.liderahenk.lider.report;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.org.liderahenk.lider.core.api.persistence.dao.IReportDao;
import tr.org.liderahenk.lider.core.api.persistence.entities.IReportTemplate;
import tr.org.liderahenk.lider.core.api.persistence.factories.IEntityFactory;
import tr.org.liderahenk.lider.core.api.plugin.BaseReportTemplate;

/**
 * This class listens to new installed bundles on Lider server and manages their
 * report templates if an implementation of {@link BaseReportTemplate} is
 * provided.
 * 
 * @author <a href="mailto:emre.akkaya@agem.com.tr">Emre Akkaya</a>
 *
 */
public class TemplateManager {

	private Logger logger = LoggerFactory.getLogger(TemplateManager.class);

	private List<IReportTemplate> templateList;
	private IReportDao reportDao;
	private IEntityFactory entityFactory;
	private IReportTemplate taskTemplate;
	private IReportTemplate onlineUsersTemplate;
	private IReportTemplate agentInfoTemplate;
	private IReportTemplate installedPluginsTemplate;
	private IReportTemplate agentHardwareTemplate;
	private IReportTemplate sessionActivityTemplate;
	private IReportTemplate biosDistroChart;

	public void init() {
		// Check if task template already exists!
		List<? extends IReportTemplate> templates = reportDao.findTemplates("name", taskTemplate.getName(), 1);
		IReportTemplate existingTemplate = templates != null && !templates.isEmpty() ? templates.get(0) : null;
		if (existingTemplate != null) {
			existingTemplate = entityFactory.createReportTemplate(existingTemplate, taskTemplate);
			reportDao.updateTemplate(existingTemplate);
		} else {
			reportDao.saveTemplate(taskTemplate);
		}
		// Check if online users template already exists!
		List<? extends IReportTemplate> onlineUserstemplates = reportDao.findTemplates("name",
				onlineUsersTemplate.getName(), 1);
		IReportTemplate existingUserTemplate = onlineUserstemplates != null && !onlineUserstemplates.isEmpty()
				? onlineUserstemplates.get(0) : null;
		if (existingUserTemplate != null) {
			existingUserTemplate = entityFactory.createReportTemplate(existingUserTemplate, onlineUsersTemplate);
			reportDao.updateTemplate(existingUserTemplate);
		} else {
			reportDao.saveTemplate(onlineUsersTemplate);
		}
		// Check if agent info template already exists!
		List<? extends IReportTemplate> agentInfoTemplates = reportDao.findTemplates("name",
				agentInfoTemplate.getName(), 1);
		IReportTemplate existingAgentInfoTemplate = agentInfoTemplates != null && !agentInfoTemplates.isEmpty()
				? agentInfoTemplates.get(0) : null;
		if (existingAgentInfoTemplate != null) {
			existingAgentInfoTemplate = entityFactory.createReportTemplate(existingAgentInfoTemplate,
					agentInfoTemplate);
			reportDao.updateTemplate(existingAgentInfoTemplate);
		} else {
			reportDao.saveTemplate(agentInfoTemplate);
		}
		// Check if installed plugins template already exists!
		List<? extends IReportTemplate> installedPluginsTemplates = reportDao.findTemplates("name",
				installedPluginsTemplate.getName(), 1);
		IReportTemplate existingInstalledPluginsTemplate = installedPluginsTemplates != null
				&& !installedPluginsTemplates.isEmpty() ? installedPluginsTemplates.get(0) : null;
		if (existingInstalledPluginsTemplate != null) {
			existingInstalledPluginsTemplate = entityFactory.createReportTemplate(existingInstalledPluginsTemplate,
					installedPluginsTemplate);
			reportDao.updateTemplate(existingInstalledPluginsTemplate);
		} else {
			reportDao.saveTemplate(installedPluginsTemplate);
		}
		// Check if agent hardware template already exists!
		List<? extends IReportTemplate> agentHardwareTemplates = reportDao.findTemplates("name",
				agentHardwareTemplate.getName(), 1);
		IReportTemplate existingAgentHardwareTemplate = agentHardwareTemplates != null
				&& !agentHardwareTemplates.isEmpty() ? agentHardwareTemplates.get(0) : null;
		if (existingAgentHardwareTemplate != null) {
			existingAgentHardwareTemplate = entityFactory.createReportTemplate(existingAgentHardwareTemplate,
					agentHardwareTemplate);
			reportDao.updateTemplate(existingAgentHardwareTemplate);
		} else {
			reportDao.saveTemplate(agentHardwareTemplate);
		}
		// Check if session activity template already exists!
		List<? extends IReportTemplate> sessionActivityTemplates = reportDao.findTemplates("name",
				sessionActivityTemplate.getName(), 1);
		IReportTemplate existingSessionActivityTemplate = sessionActivityTemplates != null
				&& !sessionActivityTemplates.isEmpty() ? sessionActivityTemplates.get(0) : null;
		if (existingSessionActivityTemplate != null) {
			existingSessionActivityTemplate = entityFactory.createReportTemplate(existingSessionActivityTemplate,
					sessionActivityTemplate);
			reportDao.updateTemplate(existingSessionActivityTemplate);
		} else {
			reportDao.saveTemplate(sessionActivityTemplate);
		}
		// Check if bios distro template already exists!
		List<? extends IReportTemplate> biosDistroTemplates = reportDao.findTemplates("name", biosDistroChart.getName(),
				1);
		IReportTemplate existingBiosDistroTemplate = biosDistroTemplates != null && !biosDistroTemplates.isEmpty()
				? biosDistroTemplates.get(0) : null;
		if (existingBiosDistroTemplate != null) {
			existingBiosDistroTemplate = entityFactory.createReportTemplate(existingBiosDistroTemplate,
					biosDistroChart);
			reportDao.updateTemplate(existingBiosDistroTemplate);
		} else {
			reportDao.saveTemplate(biosDistroChart);
		}
	}

	public void destroy() {
		logger.info("Destroying template manager...");
	}

	private void registerTemplates() {

		if (templateList != null && !templateList.isEmpty()) {

			for (IReportTemplate template : templateList) {

				if (template.getName() == null || template.getName().isEmpty() || template.getQuery() == null
						|| template.getQuery().isEmpty()) {
					logger.warn("Template name and query can't be empty or null. Passing registration of template: {}");
					continue;
				}

				try {
					// Check if the template already exists
					List<? extends IReportTemplate> templates = reportDao.findTemplates("name", template.getName(), 1);
					IReportTemplate temp = templates != null && !templates.isEmpty() ? templates.get(0) : null;

					if (temp != null) {
						// Template already exists! Update its properties
						temp = entityFactory.createReportTemplate(temp, template);
						temp = reportDao.updateTemplate(temp);
					} else {
						// Create new template!
						temp = entityFactory.createReportTemplate(template);
						temp = reportDao.saveTemplate(temp);
					}

				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}

			}

		}

	}

	/**
	 * 
	 * @param templateList
	 */
	public void setTemplateList(List<IReportTemplate> templateList) {
		this.templateList = templateList;
		registerTemplates();
	}

	/**
	 * 
	 * @param reportDao
	 */
	public void setReportDao(IReportDao reportDao) {
		this.reportDao = reportDao;
	}

	/**
	 * 
	 * @param entityFactory
	 */
	public void setEntityFactory(IEntityFactory entityFactory) {
		this.entityFactory = entityFactory;
	}

	/**
	 * 
	 * @param taskTemplate
	 */
	public void setTaskTemplate(IReportTemplate taskTemplate) {
		this.taskTemplate = taskTemplate;
	}

	/**
	 * 
	 * @param onlineUsersTemplate
	 */
	public void setOnlineUsersTemplate(IReportTemplate onlineUsersTemplate) {
		this.onlineUsersTemplate = onlineUsersTemplate;
	}

	/**
	 * 
	 * @param agentInfoTemplate
	 */
	public void setAgentInfoTemplate(IReportTemplate agentInfoTemplate) {
		this.agentInfoTemplate = agentInfoTemplate;
	}

	/**
	 * 
	 * @param installedPluginsTemplate
	 */
	public void setInstalledPluginsTemplate(IReportTemplate installedPluginsTemplate) {
		this.installedPluginsTemplate = installedPluginsTemplate;
	}

	/**
	 * 
	 * @param agentHardwareTemplate
	 */
	public void setAgentHardwareTemplate(IReportTemplate agentHardwareTemplate) {
		this.agentHardwareTemplate = agentHardwareTemplate;
	}

	/**
	 * 
	 * @param sessionActivityTemplate
	 */
	public void setSessionActivityTemplate(IReportTemplate sessionActivityTemplate) {
		this.sessionActivityTemplate = sessionActivityTemplate;
	}

	/**
	 * 
	 * @param biosDistroChart
	 */
	public void setBiosDistroChart(IReportTemplate biosDistroChart) {
		this.biosDistroChart = biosDistroChart;
	}

}
