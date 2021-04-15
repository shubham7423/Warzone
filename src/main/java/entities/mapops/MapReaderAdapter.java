package entities.mapops;

import entities.GameMap;

/**
 * Adapter for map reader.
 *
 */
public class MapReaderAdapter extends ReadMap {
	private ConquestReadMap d_conquestReader;
	
	/**
	 * MapReaderAdapter constructor
	 * @param p_conquestReader ConquestReader object
	 * @param p_gameMap GameMap object
	 */
	public MapReaderAdapter(ConquestReadMap p_conquestReader, GameMap p_gameMap) {
		super(p_gameMap);
		d_conquestReader = p_conquestReader;
	}
	
	/**
	 * function to read a file feed data to GameMap object.
	 * @param p_filePath path to .map file
	 * @return true if file load successfully, else false
	 */
	@Override
	public boolean readFullMap(String p_filePath) {
		return d_conquestReader.readFullMap(p_filePath); 
	}
}
