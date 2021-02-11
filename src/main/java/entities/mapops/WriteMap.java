/**
 * Class to write into map file
 * 
 */

public class WriteMap{
	GameMap d_gameMap;
	HashMap<String, Continent> d_continentsMap;
	HashMap<String, Country> d_countriesMap;
	BufferedWriter d_writer;
	
	/**
	 * Constructor 
	 * @param p_gameMap GameMap object
	 */
	WriteMap (GameMap p_gameMap) {
		d_gameMap = p_gameMap;
		d_continentsMap = new HashMap<>();
		d_countriesMap = new HashMap<>();
	}
	
	public boolean writeFullMap (String p_filePath) {
		ClassLoader classLoader = Thread.currentThread().getContextLoader();
		
		File l_mapFile = new java.io.File(classLoader.getResource(p_filePath).getFile());
		String l_line, l_dataString;
		int l_countryCtn = 0, l_continentCtn = 0;
		
		try {
			d_writer = new BufferedWriter(l_mapFile);
			
			//Writing Continents
			continentWriter() throws IOException {
				d_continentsMap = d_gameMap.getContinents();
				
				d_writer.write("[continents]");
				for (Continent l_continent : d_continentsMap) {
					d_writer.write(l_continent.getName()  + " " + l_continent.getControlValue())
					
				}
			}
			
			
		} catch () {
			
		}
	}
	
}