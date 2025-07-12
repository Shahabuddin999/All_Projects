package com.olx.controller;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.olx.dto.AdvertiseDto;
import com.olx.service.AdvertiseService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
 
@RestController
@RequestMapping("/advertiseMasterApp")
@CrossOrigin(origins = "*")
public class AdvertiseController {
	
	@Autowired
	AdvertiseService advertiseService;
	
	@Autowired
	RestTemplate restTemplate;
	
	// Swagger: http://localhost:8085/swagger-ui/index.html
	// Always send Bearer token from postman or Swagger. if you send from postman then go to Authorization->"Auth Type" select "Bearer Tocken" in drop down and past tocken. 
	
	@PostMapping(value="/advertise", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Create a Advertise", notes = "Create a Advertise and Returns Adertise to Client") // This @ApiOperation and @ApiParam is belonging to swagger
	public ResponseEntity<AdvertiseDto>  createAdvertise(@RequestBody @ApiParam(value="Need to send Advertise DTO", name = "advertiseDto", required = true) AdvertiseDto advertiseDto,
														 @ApiParam(value="Need to send Authorization", name = "Authorization", required = true) HttpServletRequest request) {
		String tocken = getBearerTocken(request);	
		ResponseEntity<AdvertiseDto> responseAdvertiseDto = advertiseService.createAdvertise(advertiseDto, tocken);
		return responseAdvertiseDto;
	}
	
	@PutMapping(value="/advertise/{advertiseId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Update particular Advertise", notes = "Update particular Advertise and return to the client") // This @ApiOperation and @ApiParam is belonging to swagger
	public ResponseEntity<AdvertiseDto>  updateAdvertise(@RequestBody @ApiParam(value="Send advertiseDto DTO", name="advertiseDto", required = true) AdvertiseDto advertiseDto,@ApiParam(value="Send Authorization", name="Authorization", required = true)  HttpServletRequest request, @ApiParam(value="Advertise Id", name="advertiseId", required = true) @PathVariable("advertiseId") int advertiseId) {
		ResponseEntity<AdvertiseDto> response = advertiseService.updateAdvertise(advertiseDto, getBearerTocken(request), advertiseId);
		return response;
	}
	
	@GetMapping(value="/user/advertise", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Read all Advertise", notes = "Returns a particular Advertise and return to the client") // This @ApiOperation and @ApiParam is belonging to swagger
	public ResponseEntity<List<AdvertiseDto>>  getAdvertiseByLoggedInUser(@ApiParam(value="Auth Tocken over here", name="Authorization", required = true) HttpServletRequest request) {
		ResponseEntity<List<AdvertiseDto>> response = advertiseService.getAdvertiseByLoggedInUser(getBearerTocken(request));
		return response;
	}
	
	@GetMapping(value="/user/advertise/{advertiseId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Read Advertise based on AuthTocken and advertiseID", notes = "Returns Read all Advertise based on AuthTocken to the client") // This @ApiOperation and @ApiParam is belonging to swagger
	public ResponseEntity<AdvertiseDto>  getAdvertiseByLoggedInUserAndId(@ApiParam(value="Send Authorization", name="Authorization", required = true) HttpServletRequest request, @ApiParam(value="Advertise Id", name="advertiseId", required = true) @PathVariable("advertiseId") int advertiseId) {
		ResponseEntity<AdvertiseDto> response = advertiseService.getAdvertiseByLoggedInUserAndId(getBearerTocken(request), advertiseId);
		return response;
	}
	
	@DeleteMapping(value="/user/advertise/{advertiseId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Read and Delete advertise for a particular advertise Id", notes = "Read and Delete advertise for a particular advertise Id and return to the client") // This @ApiOperation and @ApiParam is belonging to swagger
	public ResponseEntity<Boolean>  deleteAdvertiseByLoggedInUserAndId(@ApiParam(value="Send Authorization", name="Authorization", required = true) HttpServletRequest request,@ApiParam(value="Advertise Id", name="advertiseId", required = true) @PathVariable("advertiseId") int advertiseId) {
		ResponseEntity<Boolean> response = advertiseService.deleteAdvertiseByLoggedInUserAndId(getBearerTocken(request), advertiseId);
		return response;
	}
	
	@GetMapping(value="/advertise/search/filtercriteria", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Read all advertise based on search filter", notes = "Read and all advertise based on search filter and return to the client") // This @ApiOperation and @ApiParam is belonging to swagger
	public ResponseEntity<List<AdvertiseDto>>  getAdvertiseByCriteria(
			@RequestParam(name = "searchText", required=false,defaultValue = "") String searchText,
			@RequestParam(name = "category", required=false) int category,
			@RequestParam(name = "postedBy", required=false, defaultValue = "") String postedBy,
			@RequestParam(name = "dateCondition", required=false, defaultValue = "") String dateCondition,
			@RequestParam(name = "onDate", required=false, defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate onDate,
			@RequestParam(name = "fromDate", required=false, defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
			@RequestParam(name = "toDate", required=false, defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
			@RequestParam(name = "sortBy", required=false, defaultValue = "") String sortBy,
			@RequestParam(name = "startIndex", required=false, defaultValue = "0") int startIndex,
			@RequestParam(name = "records", required=false, defaultValue = "10") int records) {
		
			return advertiseService.getAdvertiseByCriteria(searchText, category, postedBy, dateCondition, onDate, fromDate, toDate, sortBy, startIndex, records);
	
	}
	
	@GetMapping(value="/advertise/searchFields/filtercriteria", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Read all advertise based on search filter", notes = "Read and all advertise based on search filter and return to the client") // This @ApiOperation and @ApiParam is belonging to swagger
	public List<AdvertiseDto> searchAdvertisesByFilterCriteria(@RequestParam(name="searchText", required = false)String searchText,
	@RequestParam(name = "category", required = false, defaultValue = "0")int categoryId, @RequestParam(name="postedBy", required=false)String postedBy,
	@RequestParam(name="dateCondition", required=false)String dateCondition,
	@RequestParam(name="onDate", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate onDate,
	@RequestParam(name="fromDate", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
	@RequestParam(name="toDate", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
	@RequestParam(name="sortedBy", required=false)String sortedBy, @RequestParam(name = "startIndex", defaultValue="0")int startIndex, @RequestParam(name="records", defaultValue = "10")int records
	) {
		List<AdvertiseDto> list = advertiseService.searchAdvertisesByFilterCriteria(searchText, categoryId, postedBy, dateCondition,
				onDate, fromDate, toDate, sortedBy, startIndex, records);
		return list;
	}
	
	@GetMapping(value="/advertise/search/text", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Read all advertise based on search Text", notes = "Read and all advertise based on search Text and return to the client") // This @ApiOperation and @ApiParam is belonging to swagger
	public ResponseEntity<List<AdvertiseDto>>  getAdvertiseByText(@ApiParam(value="Advertise Text for Title and Description", name="searchText", required = true) @RequestParam("searchText") String searchText) {
		ResponseEntity<List<AdvertiseDto>> response = advertiseService.getAdvertiseByText(searchText);
		return response;
	}
	
	@GetMapping(value="/advertise/{advertiseId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value="Read particular advertise based on id", notes = "Read particular advertise based on id and returns... to the client") // This @ApiOperation and @ApiParam is belonging to swagger
	public ResponseEntity<AdvertiseDto>  getAdvertiseById(@ApiParam(value="Send Authorization", name="Authorization", required = true) HttpServletRequest request,@ApiParam(value="Advertise Id", name="advertiseId", required = true) @PathVariable("advertiseId") int advertiseId) {
		// in the doc API no 11 and 15 are same but only response is different
		// means this deleteAdvertiseByLoggedInUserAndId() and getAdvertiseById()
		ResponseEntity<AdvertiseDto> response = advertiseService.getAdvertiseById(getBearerTocken(request), advertiseId);
		return response; 
	}
	
	public String getBearerTocken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (!token.startsWith("Bearer "))
			token = "Bearer " + token;
		return token;
	}
}
