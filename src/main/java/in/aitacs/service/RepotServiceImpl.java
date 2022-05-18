package in.aitacs.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import in.aitacs.entity.EligibilityDtlsEntity;
import in.aitacs.repository.ElgiDtlsRepository;
import in.aitacs.request.SearchRequest;
import in.aitacs.response.SearchResponse;

@Service
public class RepotServiceImpl implements ReportsService {

	@Autowired
	private ElgiDtlsRepository elgiDtlsRepository;

	@Override
	public List<String> getPlanNames() {

		return elgiDtlsRepository.getUniquePlans();
	}

	@Override
	public List<String> getPlanStatus() {

		return elgiDtlsRepository.getUniqueStatus();
	}

	@Override
	public List<SearchResponse> getSearchPlans(SearchRequest request) {

		List<EligibilityDtlsEntity> elgibleRec = null;
		
		if (isSearchReqEmpty(request)) {
			elgibleRec = elgiDtlsRepository.findAll();
			
		} else {

			String planName = request.getPlanName();
			String planStatus = request.getPlanStatus();
			LocalDate startDate = request.getStartDate();
			LocalDate endDate = request.getEnddate();

			EligibilityDtlsEntity entity = new EligibilityDtlsEntity();

			if (planName != null && !planName.equals("")) {
				entity.setPlanName(planName);
			}

			if (planStatus != null && !planStatus.equals("")) {
				entity.setPlanStatus(planStatus);
			}

			if (startDate != null && endDate != null) {
				entity.setStartDate(startDate);
				entity.setEnddate(endDate);
			}

			Example<EligibilityDtlsEntity> of = Example.of(entity);
			elgibleRec = elgiDtlsRepository.findAll(of);
		}
		List<SearchResponse> response = new ArrayList<SearchResponse>();

		for (EligibilityDtlsEntity records : elgibleRec) {

			SearchResponse s = new SearchResponse();
			BeanUtils.copyProperties(records, s);
			response.add(s);

		}

		// user can select only plan-name can click on search button
		// user can select only plan-status can click on search button
		// user can select both plan-name and plan-status click on search button

		// user can select start-date & end-date and click on search button

		// user can select plan-name, start-date , end-date and click on search button
		// user can select plan-status, start-date, end-date and click on search button
		// user can select plan-name, plan-status, start-date, end-date and click on
		// search button
		
		return response;

	}
	
	private boolean isSearchReqEmpty(SearchRequest request) {
		
		if(request.getPlanName()!= null && !request.getPlanName().equals("")) {
			return false;
		}
		
		if(request.getPlanStatus()!= null && !request.getPlanStatus().equals("")) {
			return false;
		}
		
		if(request.getStartDate()!= null && !request.getStartDate().equals("")) {
			return false;
		}
		
		if(request.getEnddate()!= null && !request.getEnddate().equals("")) {
			return false;
		}
		
		return true;
	}

}
