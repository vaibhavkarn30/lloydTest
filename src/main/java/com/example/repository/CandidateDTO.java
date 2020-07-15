/**
 * DTO Layer
 * Author:Vaibhav Karn
 */

package com.example.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Candidate;

/**
 * Repository Extending CrudRepository
 * 
 * @author vaikarn
 *
 */
@Repository
public interface CandidateDTO extends CrudRepository<Candidate, Integer> {

	List<Candidate> findByPlace(String place);

	List<Candidate> findByTitle(String title);

	List<Candidate> findByUnit(String unit);

	List<Candidate> findBySupid(String supid);
}
