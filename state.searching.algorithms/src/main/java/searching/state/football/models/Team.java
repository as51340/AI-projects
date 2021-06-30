package searching.state.football.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import searching.state.football.constants.Constants;
import searching.state.football.positions.Position;

/**
 * Class for football team. It will also be used as state in the context of AI search state
 * algorithms
 * @author Andi Škrgat
 *
 */
public class Team {
	
	/**
	 * Team name
	 */
	private String name;

	/**
	 * Team's demanded formation
	 */
	private Formation formation;
	
	/**
	 * List of positions
	 */
	private List<Position> positions;
	
	/**
	 * Array of empty positions
	 */
	private short emptyPositions[] = {0, 0};

	/**
	 * Init constructor
	 * @param name
	 * @param formation
	 * @param positions
	 */
	public Team(String name, Formation formation, List<Position> positions) {
		super();
		this.name = name;
		this.formation = formation;
		this.positions = positions;	
		calculateEmptyPositions();
	}
	
	/**
	 * Deep copy
	 * @param team
	 */
	public Team(Team team) {
		super();
		this.name = new String(team.getName());
		this.formation = team.getFormation();
		List<Position> newPositions = new ArrayList<Position>();
		for(Position p: team.getPositions()) {
			newPositions.add(new Position(p));
		}
		this.positions = newPositions;
	}
	
	/**
	 * Calculates empty positions from given input of positions
	 */
	public void calculateEmptyPositions() {
		short j = 0;
		for(short i = 0; i < Constants.stateSize; i++) {
			if(j >= 2) break;
			if(positions.get(i).getName().equals("*")) {
				emptyPositions[j++] = i;
			}
		}
		if(j != 2) throw new IllegalStateException("We don't have two empty positions!");
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Formation getFormation() {
		return formation;
	}
	
	public void setFormation(Formation formation) {
		this.formation = formation;
	}
	
	public List<Position> getPositions() {
		return positions;
	}
	
	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public short[] getEmptyPositions() {
		return emptyPositions;
	}
	
	public void setPosition(int index, Position position) {
		this.positions.set(index, position);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(emptyPositions);
		result = prime * result + ((formation == null) ? 0 : formation.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((positions == null) ? 0 : positions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		if (!Arrays.equals(emptyPositions, other.emptyPositions))
			return false;
		if (formation != other.formation)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (positions == null) {
			if (other.positions != null)
				return false;
		} else if (!positions.equals(other.positions))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(formation.toString() + "\n");
		for(int i = 0; i < Constants.colSize; i++) {
			for(int j = 0; j < Constants.rowSize; j++) {
				sb.append(positions.get(i * Constants.rowSize + j)+ " ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	
	
	
	
	
	
	
	
	
}
