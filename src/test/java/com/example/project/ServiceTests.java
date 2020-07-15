/**
 * Service Test Cases
 * Author:Vaibhav Karn
 */
package com.example.project;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.model.Candidate;
import com.example.repository.CandidateDTO;
import com.example.service.CandidateService;
import org.mockito.junit.MockitoJUnitRunner;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class ServiceTests {
	
	@Mock
	private CandidateDTO cdto;
	
	@InjectMocks
	private CandidateService candidateService;
	
	/**
	 * Test Case for GetSalaryRangeByTitle where DTO layer specific methods are mocked.Similarly Other Test Cases Can be Written
	 */
	@Test
	public void  getSalaryRangeByTitle() {	
		List<Candidate> mockList=new ArrayList<Candidate>();
		mockList.add(new Candidate("vaibhav","sde","pol","ggn","101","Java",3000));
		when(cdto.findByTitle(Mockito.anyString())).thenReturn(mockList);
		assertEquals("3000.0-3000.0", candidateService.getSalaryRangeByTitle("test"));		
	}
}
