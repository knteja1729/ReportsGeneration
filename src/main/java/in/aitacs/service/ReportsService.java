package in.aitacs.service;

import java.util.List;


import in.aitacs.request.SearchRequest;
import in.aitacs.response.SearchResponse;

public interface ReportsService {
	
	public List<String> getPlanNames();
	
	public List<String> getPlanStatus();
	
	public List<SearchResponse> getSearchPlans(SearchRequest request);

}
