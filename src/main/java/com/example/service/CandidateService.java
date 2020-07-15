/**
 * Service Class
 * Author:Vaibhav Karn
 */

package com.example.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.*;
import com.example.model.*;

@Service
public class CandidateService {

	@Autowired
	private CandidateDTO cdto;

	public List<String> hierarchyList = new ArrayList<String>();

	/*
	 * Method to Save Candidate in DB
	 */
	public void saveCandidateData() {
		try {
			String line = "";
			BufferedReader br = new BufferedReader(new FileReader("src/main/resources/record.csv"));
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");
				Candidate c = new Candidate(data[0], data[1], data[2], data[3], data[4], data[5],
						Double.valueOf(data[6]));
				cdto.save(c);
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/*
	 * Method to find CandidateByPlace
	 */
	public List<Candidate> getCandidateByPlace(String place) {
		return cdto.findByPlace(place);
	}
	
    /*
     * Method to fetch Salary Range Based on Title
     */
	public String getSalaryRangeByTitle(String title) {
		List<Candidate> candidatelist = cdto.findByTitle(title);
		double maxsalary = Double.MIN_VALUE;
		double minsalary = Double.MAX_VALUE;
		for (Candidate candidate : candidatelist) {
			if (candidate.getSalary() > maxsalary)
				maxsalary = candidate.getSalary();
			if (candidate.getSalary() < minsalary)
				minsalary = candidate.getSalary();
		}
		return String.valueOf(minsalary) + "-" + String.valueOf(maxsalary);
	}
	
    /*
     * Method to fetch Total Salary By Business Unit
     */
	public String getTotalSalaryByBusinessUnit(String unit) {
		List<Candidate> candidateList = cdto.findByUnit(unit);
		return totalSalary(candidateList);
	}
	
    /*
     * Method to fetch Total Salary By Supervisor
     */
	public String getTotalSalaryBySupervisor(String supid) {
		List<Candidate> candidateList = cdto.findBySupid(supid);
		return totalSalary(candidateList);
	}
	
    /*
     * Method to Fetch Total Salary By Place
     */
	public String getTotalSalaryByPlace(String place) {
		List<Candidate> candidateList = cdto.findByPlace(place);
		return totalSalary(candidateList);
	}

	/*
	 * Common Method for calculating Salary
	 */
	public String totalSalary(List<Candidate> candidatelist) {
		double sum = 0;
		for (Candidate candidate : candidatelist) {
			sum += candidate.getSalary();
		}
		return String.valueOf(sum);
	}
	
    /*
     * Method to update Salary of the Candidates
     */
	public void updateSalary(List<Candidate> candidateList) {
		cdto.saveAll(candidateList);
	}
   
	/*
	 * Method to get the hierarichal List of the Organisation
	 */
	public List<String> superviseeList(String supid) {
		hierarchyList.clear();
		List<String> tempList = new ArrayList<String>();
		supList(supid, tempList);
		return hierarchyList;

	}
    
	/*
	 * Method used for recrsion calls to get superviseeList
	 */
	public void supList(String supid, List<String> tempList) {
		if (supid != null) {
			tempList.add(supid);
			List<Candidate> list = cdto.findBySupid(supid);
			if (!list.isEmpty()) {
				for (Candidate c : list) {
					supList(c.getName(), tempList);
				}

			} else {
				String res = "";
				for (String str : tempList) {
					res = res + str + " ";
				}
				hierarchyList.add(res);
			}
			tempList.remove(tempList.size() - 1);
			return;
		}

	}
}
