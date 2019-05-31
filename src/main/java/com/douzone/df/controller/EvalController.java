package com.douzone.df.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.df.model.Eval;
import com.douzone.df.model.EvalItem;
import com.douzone.df.model.EvalItemScore;
import com.douzone.df.model.EvalItemVersion;
import com.douzone.df.payload.EvalRankRequest;
import com.douzone.df.payload.EvalRankResponse;
import com.douzone.df.payload.EvalScoreRequest;
import com.douzone.df.payload.EvalVersionRequest;
import com.douzone.df.payload.MonthNYearRankRequest;
import com.douzone.df.payload.QuarterNHalfRankRequest;
import com.douzone.df.payload.RankResponse;
import com.douzone.df.service.EvalService;


@RestController
@RequestMapping("/api/eval")
public class EvalController {

	@Autowired
	private EvalService service;
	

	@PostMapping("/createVersion")
	public boolean createVersion(@RequestBody EvalVersionRequest request) {
		boolean result = service.createEvalVersion(request);
		
		return result; // 정상실행 : true / 실패 : false
	}
	
	@GetMapping("/getVersionObj")
	public List<EvalItemVersion> getVersionObj(@RequestParam("selectedVersion")String versionName) {
		return service.getVersionObj(versionName);
	}

	// READ All
	@GetMapping("/getAllVersion")
	public List<String> getAllVersion() {
		List<String> versionList = service.findAllEvalVersion();
		return versionList;
	}

	@GetMapping("/getVersion")
	public List<EvalItem> getVersion(@RequestParam("version")String version) {
		return service.findEvalItemByVersion(version);
	}	
		
	@RequestMapping(value = "/searchEvalScore", method=RequestMethod.POST)
	@ResponseBody
	public EvalItemScore searchEvalScore(EvalItemScore evalItemScore) {
		// url로 넘어온 no값으로 객체 찾기		
		EvalItemScore findItemScore = service.findItemScore(evalItemScore);
		
		return findItemScore; // 만약 null이 넘어온다면 객체를 찾지 못한 경우
	}

	@PostMapping("/setEval")
	public List<Eval> setEval(@RequestBody EvalScoreRequest request) {
		return service.setEval(request);
	}	

	@PostMapping("/updateEval")
	public boolean updateEval(@RequestBody EvalScoreRequest request) {
		return service.updateEval(request);
	}
	
	@GetMapping("/searchEval")
	public List<Eval> isExistEval(@RequestParam("taskId")Long taskId) {
		return service.searchEval(taskId);
	}
	
	@PostMapping("/rank")
	public EvalRankResponse rank(@RequestBody EvalRankRequest tasks) {
		return service.rank(tasks);
	}
	
	@GetMapping("/getScoreByReport")
	public double getScoreByReport(@RequestParam("taskId") Long taskId) {
		return service.getScoreByReport(taskId);
	}

	@GetMapping("/searchYear")
	public ArrayList<String> searchYear() {
		return service.searchYear();
	}
	
	@PostMapping("isExistUserInEval")
	public ArrayList<Long> isExistUserInEval(@RequestBody ArrayList<Long> taskIds) {
		return service.isExistUserInEval(taskIds);
	}

	// 평가순위 검색 조건
	// 월간 & 년
	@PostMapping("/monthNYearRank")
	public ArrayList<RankResponse> monthNYearRank(@RequestBody MonthNYearRankRequest param) {
		return service.monthNYearRank(param);
	}

	// 분기 & 반기
	@PostMapping("/quarterNHalfRank")
	public ArrayList<RankResponse> quarterNHalfRank(@RequestBody QuarterNHalfRankRequest param) {
		return service.quarterNHalfRank(param);
	}

}
