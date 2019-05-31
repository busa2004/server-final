package com.douzone.df.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.df.model.Eval;
import com.douzone.df.model.EvalItem;
import com.douzone.df.model.EvalItemScore;
import com.douzone.df.model.EvalItemVersion;
import com.douzone.df.model.UserTask;
import com.douzone.df.payload.EvalRankRequest;
import com.douzone.df.payload.EvalRankResponse;
import com.douzone.df.payload.EvalScoreRequest;
import com.douzone.df.payload.EvalUserScore;
import com.douzone.df.payload.EvalVersionRequest;
import com.douzone.df.payload.MonthNYearRankRequest;
import com.douzone.df.payload.QuarterNHalfRankRequest;
import com.douzone.df.payload.RankResponse;
import com.douzone.df.repository.EvalItemRepository;
import com.douzone.df.repository.EvalItemScoreReopsitory;
import com.douzone.df.repository.EvalItemVersionRepository;
import com.douzone.df.repository.EvalRepository;

@Service
public class EvalService {

	@Autowired
	private EvalRepository evalRepo;
	@Autowired
	private EvalItemRepository itemRepo;
	@Autowired
	private EvalItemScoreReopsitory scoreRepo;
	@Autowired
	private EvalItemVersionRepository versionRepo;

	public boolean createEvalItem(EvalItem evalItem) {
		itemRepo.save(evalItem); // insert작업..

		Long id = evalItem.getItemNo();
		boolean result = itemRepo.existsById(id);

		return result;
	}

	public boolean createEvalVersion(EvalVersionRequest request) {
		boolean result = false;

		String version = request.getVersion();
		List<EvalItem> items = request.getDataSource();

		// Create Eval Item
		for (EvalItem item : items) {
			item.setItemNo(null);
			result = createEvalItem(item);
			if (result == false) {
				// 실패했을 때 돌아감..
				return result;
			}
		}

		result = false;

		for (int i = 0; i < items.size(); i++) {
			// 여기서 객체 생성해준 이유 : 같은 객체에서 setter작업만 일어나면 값이 변동되는줄 알고 update 가 일어난다.
			EvalItemVersion evalVersion = new EvalItemVersion();

			Long itemNo = items.get(i).getItemNo();
			EvalItem itemObj = itemRepo.findById(itemNo).orElse(null);
			System.out.println(itemObj);

			evalVersion.setVersion(version);
			evalVersion.setEvalItem(itemObj);

			versionRepo.saveAndFlush(evalVersion);

			// Test..
			// 정상저장되었는지 확인
			Long id = evalVersion.getItemVersionNo();
			System.out.println("====" + id);
			result = versionRepo.existsById(id);

			if (result == false) {
				// 저장 실패
				break;
			}
		}
		return result;
	}

	public List<EvalItemVersion> getVersionObj(String versionName) {
		return versionRepo.findByVersion(versionName);
	}

	public List<String> findAllEvalVersion() {
		List<String> versionList = versionRepo.findAllVersionName();

		return versionList;
	}

	public List<EvalItem> findEvalItemByVersion(String version) {
		List<EvalItem> findVersion = versionRepo.findEvalItemByVersion(version);
		return findVersion;
	}

	public EvalItemScore findItemScore(EvalItemScore evalItemScore) {
		Long id = evalItemScore.getItemScoreNo();

		EvalItemScore findItemScore = scoreRepo.findById(id).orElse(null);

		return findItemScore;
	}

	public List<Eval> setEval(EvalScoreRequest request) {
		ArrayList<EvalItemScore> scores = request.getScores();
		UserTask userTask = request.getUserTask();
		Long userTaskId = userTask.getId();
		Long userId = request.getUserId();
		
		List<Eval> evals = new ArrayList<Eval>();
		
		for (EvalItemScore score : scores) {
			score.setUserTaskId(userTaskId);
			score.setUserId(userId);
			
			scoreRepo.save(score);
			
			Eval eval = new Eval();
			eval.setScore(score);
			eval.setUserTask(userTask);
			eval.setUserId(userId);
			
			evalRepo.save(eval);
			evals.add(eval);
		}

		return evals;
	}

	public boolean updateEval(EvalScoreRequest request) {
		ArrayList<EvalItemScore> scores = request.getScores();
		UserTask userTask = request.getUserTask();
		Long userTaskId = userTask.getId();
		Long userId = request.getUserId();
		
		
		for(EvalItemScore score : scores) {
			Long itemId = score.getEvalItem().getItemNo();
			Long value = score.getScore();
			System.out.println(score);
			
			// row 검색
			Long scoreRow = scoreRepo.selectRow(itemId, userId, userTaskId);
			
			scoreRepo.updateScore(scoreRow, value);
			
		}
		return true;
	}

	public List<Eval> searchEval(Long taskId) {
		return evalRepo.searchEval(taskId);
	}
	
	

	public EvalRankResponse rank(EvalRankRequest tasks) {
		ArrayList<Long> taskIds = tasks.getTaskIds();
	
		ArrayList<EvalUserScore> userScores = new ArrayList<EvalUserScore>();
		
		for(Long taskId : taskIds) {	
			// taskId로 점수리스트 가져옴
			List<Long> scores = scoreRepo.findScoreByUserTaskId(taskId);
			
			// 점수 리스트 합산
			Long sum = 0L;
			for(Long score : scores) { sum += score; }

			// taskId, 점수 합산
			EvalUserScore userScore = new EvalUserScore(); 
			userScore.setUserTaskId(taskId);
			userScore.setScore(sum);
			
			userScores.add(userScore);
		}
		
//		userScores.sort(compar);
		
		// sort
		for(int i=0; i<userScores.size()-1; i++) {
			for(int j=i+1; j<userScores.size(); j++) {
				// 점수 내림차순
				if(userScores.get(i).getScore() < userScores.get(j).getScore()) {
					EvalUserScore tmp = new EvalUserScore();
					
					tmp.setUserTaskId(userScores.get(i).getUserTaskId());
					tmp.setScore(userScores.get(i).getScore());
					
					userScores.get(i).setUserTaskId(userScores.get(j).getUserTaskId());
					userScores.get(i).setScore(userScores.get(j).getScore());
					
					userScores.get(j).setUserTaskId(tmp.getUserTaskId());
					userScores.get(j).setScore(tmp.getScore());
				}
			}
		}
		
		EvalRankResponse response = new EvalRankResponse();
		response.setUserScores(userScores);
		
		return response;
	}

	public double getScoreByReport(Long taskId) {
		Long totalReport = evalRepo.getTotalReport(taskId);
		Long holdReport = evalRepo.getHoldReport(taskId);
		System.out.println(totalReport);
		System.out.println(holdReport);
		
		double score = ((double)(totalReport - holdReport)) / totalReport * 100;
//		score = Math.round((score*100) / 100.0);
		System.out.println(score);
		
		return score;
	}

	public ArrayList<String> searchYear() {
		return evalRepo.searchYear();
	}

	public ArrayList<Long> isExistUserInEval(ArrayList<Long> taskIds) {
		ArrayList<Long> existTaskIds = new ArrayList<Long>();
		
		for(Long taskId : taskIds) {
			Long isExist = evalRepo.isExistUserInEval(taskId);
			if(isExist == 1) {
				// exist
				existTaskIds.add(taskId);
			}
		}
		
		return existTaskIds;
	}

	// 평가순위 검색 조건
	// 월간 & 년
	public ArrayList<RankResponse> monthNYearRank(MonthNYearRankRequest param) {
		ArrayList<Long> taskIds = param.getTaskIds();
		String condition = param.getCondition();
		
		ArrayList<RankResponse> userScores = new ArrayList<RankResponse>();
		
		for(Long taskId : taskIds) {
			System.out.println(taskId);
			Long totalReport = evalRepo.getTotalMonthNYearRank(taskId, condition);
			Long holdReport = evalRepo.getHoldMonthNYearRank(taskId, condition);
			
			double score = ((double)(totalReport - holdReport)) / totalReport * 100;
			
			RankResponse userScore = new RankResponse();
			userScore.setScore(score);
			userScore.setUserTaskId(taskId);
			
			userScores.add(userScore);
		}

		// sort
		rankSort(userScores);
		
		return userScores;
	}

	// 분기 & 반기
	public ArrayList<RankResponse> quarterNHalfRank(QuarterNHalfRankRequest param) {
		ArrayList<Long> taskIds = param.getTaskIds();
		String startDate = param.getStartDate();
		String endDate = param.getEndDate();
		
		ArrayList<RankResponse> userScores = new ArrayList<RankResponse>();
		for(Long taskId: taskIds) {
			Long totalReport = evalRepo.getTotalQuarterRank(taskId, startDate, endDate);
			Long holdReport = evalRepo.getHoldQuarterRank(taskId, startDate, endDate);

			double score = ((double)(totalReport - holdReport)) / totalReport * 100;
			
			RankResponse userScore = new RankResponse();
			userScore.setScore(score);
			userScore.setUserTaskId(taskId);
			
			userScores.add(userScore);			
		}

		// sort
		rankSort(userScores);
		
		return userScores;
	}
		
	public void rankSort(ArrayList<RankResponse> userScores) {
		for(int i=0; i<userScores.size()-1; i++) {
			for(int j=i+1; j<userScores.size(); j++) {
				// 점수 내림차순
				if(userScores.get(i).getScore() < userScores.get(j).getScore()) {
					RankResponse tmp = new RankResponse();
					
					tmp.setUserTaskId(userScores.get(i).getUserTaskId());
					tmp.setScore(userScores.get(i).getScore());
					
					userScores.get(i).setUserTaskId(userScores.get(j).getUserTaskId());
					userScores.get(i).setScore(userScores.get(j).getScore());
					
					userScores.get(j).setUserTaskId(tmp.getUserTaskId());
					userScores.get(j).setScore(tmp.getScore());
				}
			}
		}
	}
}
