package entities.mapops;

import entities.GameMap;

/**
 * Adapter for map writer.
 *
 */
public class MapWriterAdapter extends WriteMap {
	private ConquestWriteMap d_conquestWriter;

	/**
	 * MapWriterAdapter constructor
	 * @param p_conquestReader ConquestReader object
	 * @param p_gameMap GameMap object
	 */
	public MapWriterAdapter(ConquestWriteMap p_conquestWriter, GameMap p_gameMap) {
		super(p_gameMap);
		d_conquestWriter = p_conquestWriter;
	}

	/**
	 * method that opens a file and writes the data to that file.
	 * @param p_filePath path to .map file
	 * @return true if file saved successfully, else false
	 */
	@Override
	public boolean writeFullMap(String p_filePath) {
		return d_conquestWriter.writeFullMap(p_filePath);
	}	
}
