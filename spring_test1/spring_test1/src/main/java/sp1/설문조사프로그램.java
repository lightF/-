package sp1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class 설문조사프로그램 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		Map<String, Integer> surveyResults = new HashMap<>();

		System.out.println("설문조사를 시작합니다.");

		// 질문 1
		System.out.println("1. 좋아하는 색은 무엇인가요?");
		String color = scanner.nextLine();
		addToSurveyResults(surveyResults, color);

		// 질문 2
		System.out.println("2. 가장 좋아하는 동물은 무엇인가요?");
		String animal = scanner.nextLine();
		addToSurveyResults(surveyResults, animal);

		// 질문 3
		System.out.println("3. 오늘의 기분은 어떠신가요?");
		String mood = scanner.nextLine();
		addToSurveyResults(surveyResults, mood);

		// 결과 출력
		System.out.println("\n=== 설문 결과 ===");
		for (String question : surveyResults.keySet()) {
			int count = surveyResults.get(question);
			System.out.println(question + ": " + count + "명");
		}

		scanner.close();
	}

	private static void addToSurveyResults(Map<String, Integer> surveyResults, String answer) {
		if (surveyResults.containsKey(answer)) {
			int count = surveyResults.get(answer);
			surveyResults.put(answer, count + 1);
		} else {
			surveyResults.put(answer, 1);
		}
	}

}
