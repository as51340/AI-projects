package searching.state.football.ai.algorithms.examples;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import searching.state.football.ai.algorithms.ISearchAlgorithm;
import searching.state.football.ai.algorithms.blind.BFSAdvancedAlgorithm;
import searching.state.football.ai.algorithms.blind.BFSimpleAlgorithm;
import searching.state.football.ai.data.Node;
import searching.state.football.constants.Constants;
import searching.state.football.models.Formation;
import searching.state.football.models.Team;
import searching.state.football.positions.Position;
import searching.state.football.util.FootballFactory;
import searching.state.football.util.PositionChanger;
import searching.state.football.validators.FormationValidator;

public class Example {

	public static void main(String[] args) throws IOException {
		
		FootballFactory ff = new FootballFactory();
		ISearchAlgorithm<Team> searchAlgorithm = new BFSAdvancedAlgorithm<Team>();
		Team team = new Team("Barcelona", Formation.FOUR_FOUR_TWO, ff.createRandomFormation(Constants.positions_442));
		System.out.println(team);
		FormationValidator<Team> fv = new FormationValidator<>();
		FileWriter fw = new FileWriter("teams.out");
		Optional<Node<Team>> solution = searchAlgorithm.search(team, PositionChanger::getNextStates, fv::validate, fw);
		if(solution.isEmpty()) {
			System.out.println("There isn't any possible solution");
		} else {
			System.out.println(solution.get().getState());
		}
		System.out.println();
	}
	
}
