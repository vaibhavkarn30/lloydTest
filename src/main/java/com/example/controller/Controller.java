/**
 * Rest Controller
 * Author:Vaibhav Karn
 */
package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Candidate;
import com.example.service.CandidateService;

@RestController
@RequestMapping("/candidate")
public class Controller {

	@Autowired
	private CandidateService candidateService;

	@GetMapping("/setdata")
	public void setDataInDB() {

		candidateService.saveCandidateData();
	}

	/**
	 * 
	 * @param place
	 * @return List of Candidate by Place
	 */
	@Cacheable("updatedCandidate")
	@GetMapping("/{place}")
	public ResponseEntity<List<Candidate>> getCandidateByPlace(@PathVariable String place) {

		try {
			List<Candidate> candidateList = candidateService.getCandidateByPlace(place);
			return new ResponseEntity<>(candidateList, HttpStatus.OK);
		}

		catch (Exception ex) {

			return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * 
	 * @param title
	 * @return Salary Range By title
	 */
	@GetMapping("/range/{title}")
	public ResponseEntity<String> getSalaryRangeByTitle(@PathVariable String title) {

		try {
			String range = candidateService.getSalaryRangeByTitle(title);
			return new ResponseEntity<>(range, HttpStatus.OK);
		} catch (Exception ex) {

			return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * 
	 * @param unit
	 * @return Total Salary By BusinessUnit
	 */
	@GetMapping("/salary/unit/{unit}")
	public ResponseEntity<String> getTotalSalaryByBusinessUnit(@PathVariable String unit) {

		try {
			String salary = candidateService.getTotalSalaryByBusinessUnit(unit);
			return new ResponseEntity<>(salary, HttpStatus.OK);
		} catch (Exception ex) {

			return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * 
	 * @param supid
	 * @return Total Salary By SupervisorId
	 */
	@GetMapping("/salary/supersvisor/{supid}")
	public ResponseEntity<String> getTotalSalaryBySupervisor(@PathVariable String supid) {

		try {
			String salary = candidateService.getTotalSalaryBySupervisor(supid);
			return new ResponseEntity<>(salary, HttpStatus.OK);
		} catch (Exception ex) {

			return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * 
	 * @param place
	 * @return Total Salary By Place
	 */
	@GetMapping("/salary/place/{place}")
	public ResponseEntity<String> getTotalSalaryByPlace(@PathVariable String place) {

		try {
			String salary = candidateService.getTotalSalaryByPlace(place);
			return new ResponseEntity<>(salary, HttpStatus.OK);
		} catch (Exception ex) {

			return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * 
	 * @param place
	 * @param percentage
	 * @return List of Update
	 */
	@CachePut(value = "updatedCandidate", key = "#place")
	@PutMapping("/place/{place}/salary/{percentage}")
	public ResponseEntity<Iterable<Candidate>> updateEmployee(@RequestBody @PathVariable String place,
			@PathVariable Double percentage) {
		try {
			Iterable<Candidate> candidates = candidateService.getCandidateByPlace(place);
			Double incrementFactor = 1 + percentage / 100.0;
			candidates.forEach(employee -> employee.setSalary(employee.getSalary() * incrementFactor));
			candidateService.updateSalary((List<Candidate>) candidates);
			return new ResponseEntity<>(candidates, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/superviseelist/{supid}")
	public List<String> superviseeList(@PathVariable String supid){
		try {
			List<String> hierarchyList=candidateService.superviseeList(supid);
			return hierarchyList;
		}
		catch(Exception ex) {
			return null;
		}
	}
	
	

}
