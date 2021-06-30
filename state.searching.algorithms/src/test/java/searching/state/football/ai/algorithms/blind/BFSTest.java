package searching.state.football.ai.algorithms.blind;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import searching.state.football.ai.algorithms.ISearchAlgorithm;
import searching.state.football.ai.algorithms.informative.AStarSearchAdvanced;
import searching.state.football.ai.algorithms.informative.AStarSearchBasic;
import searching.state.football.ai.algorithms.informative.GreedyBestFirstSearch;
import searching.state.football.ai.data.Node;
import searching.state.football.ai.data.StateCostPair;
import searching.state.football.ai.heuristics.HeuristicsEuclidean;
import searching.state.football.ai.heuristics.HeuristicsManhattan;
import searching.state.football.ai.heuristics.HeuristicsNumWrong;
import searching.state.football.constants.Constants;
import searching.state.football.models.Formation;
import searching.state.football.models.Team;
import searching.state.football.positions.Position;
import searching.state.football.util.FootballFactory;
import searching.state.football.util.PositionChanger;
import searching.state.football.validators.FormationValidator;

class BFSTest {

	@Test
	void test1() throws IOException {
		FootballFactory ff = new FootballFactory();
		long time1 = System.currentTimeMillis();
		// ISearchAlgorithm<Team> searchAlgorithm = new AStarSearchAdvanced<Team>(new
		// HeuristicsNumWrong());
//		ISearchAlgorithm<Team> searchAlgorithm = new BFSimpleAlgorithm<Team>();
//		ISearchAlgorithm<Team> searchAlgorithm = new BFSAdvancedAlgorithm<Team>();
//		ISearchAlgorithm<Team> searchAlgorithm = new IterativeDFSAlgorithm<Team>();
		ISearchAlgorithm<Team> searchAlgorithm = new AStarSearchAdvanced<Team>(new HeuristicsManhattan());
		Team team = new Team("Barcelona", Formation.FOUR_FOUR_TWO, ff.createRandomFormation(Constants.positions_442));
		System.out.println(team);
		FormationValidator<Team> fv = new FormationValidator<>();
		FileWriter fw = new FileWriter("teams.txt");
		Optional<Node<Team>> solution = searchAlgorithm.search(team, PositionChanger::getNextStates, fv::validate, fw);
		if (solution.isEmpty()) {
			System.out.println("There isn't any possible solution");
		} else {
			System.out.println(solution.get().getState());
		}
		long time2 = System.currentTimeMillis();
		System.out.println(toTime(time2 - time1));
	}

	private static String toTime(long t) {
		long minuta = t / (1000 * 60);
		t -= minuta * (1000 * 60);
		long sekundi = t / (1000);
		t -= sekundi * (1000);
		return minuta + " min, " + sekundi + " sek, " + t + " tis.";
	}

	private List<Position> createPositions() {
		FootballFactory ff = new FootballFactory();
		List<Position> positions = new ArrayList<Position>();
		positions.add(new Position("LM"));
		positions.add(new Position("CB"));
		positions.add(new Position("CB"));
		positions.add(new Position("LB"));
		positions.add(new Position("RB"));
		positions.add(new Position("CM"));
		positions.add(new Position("CM"));
		positions.add(new Position("RM"));
		positions.add(new Position("*"));
		positions.add(new Position("ST"));
		positions.add(new Position("ST"));
		positions.add(new Position("*"));
		return positions;
	}

}
