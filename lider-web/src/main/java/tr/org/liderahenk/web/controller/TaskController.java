package tr.org.liderahenk.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tr.org.liderahenk.lider.core.api.rest.IResponseFactory;
import tr.org.liderahenk.lider.core.api.rest.processors.ITaskRequestProcessor;
import tr.org.liderahenk.lider.core.api.rest.responses.IRestResponse;
import tr.org.liderahenk.web.controller.utils.ControllerUtils;

/**
 * Controller for task related operations.
 * 
 * @author <a href="mailto:emre.akkaya@agem.com.tr">Emre Akkaya</a>
 *
 */
@Controller
@RequestMapping("/lider/task")
public class TaskController {

	private static Logger logger = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	private IResponseFactory responseFactory;
	@Autowired
	private ITaskRequestProcessor taskProcessor;

	@RequestMapping(value = "/execute", method = { RequestMethod.POST })
	@ResponseBody
	public IRestResponse executeTask(@RequestBody String requestBody, HttpServletRequest request)
			throws UnsupportedEncodingException {
		String requestBodyDecoded = ControllerUtils.decodeRequestBody(requestBody);
		logger.info("Request received. URL: '/lider/task/execute' Body: {}", requestBodyDecoded);
		IRestResponse restResponse = taskProcessor.execute(requestBodyDecoded);
		logger.debug("Completed processing request, returning result: {}", restResponse.toJson());
		return restResponse;
	}

	/**
	 * List commands according to given parameters.
	 * 
	 * @param pluginName
	 * @param pluginVersion
	 * @param createDateRangeStart
	 * @param createDateRangeEnd
	 * @param status
	 * @param maxResults
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/list/executed", method = { RequestMethod.GET })
	@ResponseBody
	public IRestResponse listExecutedTasks(@RequestParam(value = "pluginName", required = false) String pluginName,
			@RequestParam(value = "pluginVersion", required = false) String pluginVersion,
			@RequestParam(value = "createDateRangeStart", required = false) Long createDateRangeStart,
			@RequestParam(value = "createDateRangeEnd", required = false) Long createDateRangeEnd,
			@RequestParam(value = "status", required = false) Integer status,
			@RequestParam(value = "maxResults", required = false) Integer maxResults, HttpServletRequest request)
			throws UnsupportedEncodingException {
		logger.debug(
				"Request received. URL: '/lider/task/list/executed?pluginName={}&pluginVersion={}&createDateRangeStart={}&createDateRangeEnd={}&status={}&maxResults={}'",
				new Object[] { pluginName, pluginVersion, createDateRangeStart, createDateRangeEnd, status,
						maxResults });
		IRestResponse restResponse = taskProcessor.listExecutedTasks(pluginName, pluginVersion,
				createDateRangeStart != null ? new Date(createDateRangeStart) : null,
				createDateRangeEnd != null ? new Date(createDateRangeEnd) : null, status, maxResults);
		logger.debug("Completed processing request, returning result: {}", restResponse.toJson());
		return restResponse;
	}

	/**
	 * Retrieve command related to task specified by task id.
	 * 
	 * @param id
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/command/{id:[\\d]+}/get", method = { RequestMethod.GET })
	@ResponseBody
	public IRestResponse getCommand(@PathVariable final Long id, HttpServletRequest request)
			throws UnsupportedEncodingException {
		logger.info("Request received. URL: '/lider/task/command/{}/get'", id);
		IRestResponse restResponse = taskProcessor.getCommand(id);
		logger.debug("Completed processing request, returning result: {}", restResponse.toJson());
		return restResponse;
	}

	@RequestMapping(value = "/command/list", method = { RequestMethod.GET })
	@ResponseBody
	public IRestResponse listCommands(@RequestParam(value = "maxResults", required = false) Integer maxResults,
			HttpServletRequest request) throws UnsupportedEncodingException {
		logger.info("Request received. URL: '/lider/task/command/list?maxResults={}'", maxResults);
		IRestResponse restResponse = taskProcessor.listCommands(maxResults);
		logger.debug("Completed processing request, returning result: {}", restResponse.toJson());
		return restResponse;
	}

	/**
	 * Handle predefined exceptions that we did not write and did not throw.
	 * 
	 * @param e
	 * @return IRestResponse instance which holds exception message with ERROR
	 *         status
	 */
	@ExceptionHandler(Exception.class)
	public IRestResponse handleAllException(Exception e) {
		return ControllerUtils.handleAllException(e, responseFactory);
	}

}
