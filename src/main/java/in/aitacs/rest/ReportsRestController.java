package in.aitacs.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.aitacs.reports.ExcelGenerator;
import in.aitacs.reports.PdfGenerator;
import in.aitacs.request.SearchRequest;
import in.aitacs.response.SearchResponse;
import in.aitacs.service.ReportsService;

@RestController
public class ReportsRestController {

	@Autowired
	private ReportsService service;

	@GetMapping("/plan-names")
	public List<String> getPlans() {
		return service.getPlanNames();
	}

	@GetMapping("/plan-status")
	public List<String> getStatus() {
		return service.getPlanStatus();
	}

	@GetMapping("/excel")
	public void generateExcel(HttpServletResponse response) throws Exception {

		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Plans.xls";
		response.setHeader(headerKey, headerValue);

		List<SearchResponse> records = service.getSearchPlans(null);
		ExcelGenerator excel = new ExcelGenerator();
		excel.generateExcel(records, response);
	}

	@PostMapping("/pdf")
	public void generatePdf(@RequestBody SearchRequest request, HttpServletResponse httpResponse) throws Exception {

		httpResponse.setContentType("application/pdf");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=Plans.pdf";
		httpResponse.setHeader(headerKey, headerValue);
		//SearchRequest request = null;
		List<SearchResponse> records = service.getSearchPlans(request);
		System.out.println(records);
		PdfGenerator pdfGen = new PdfGenerator();
		pdfGen.generatePdf(records, httpResponse);
	}
	
 
	@PostMapping("/search")
	public List<SearchResponse> search(@RequestBody SearchRequest request){
		
		return service.getSearchPlans(request);
		
	}

}
