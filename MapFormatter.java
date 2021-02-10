import Country;
import dnl.utils.text.table.TextTable;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class MapFormattor {
    private Map<String, Country> d_countries;

    public MapFormattor(Map<String, Country> p_countries) {
        this.d_countries = p_countries;
    }

    public String format() {
        return this.format(FormatType.DEFAULT);
    }

    public String format(FormatType p_type) {
        String[] l_columnNames = getColumnNames(type);
        Object[][] l_data = new Object[countries.size()][columnNames.length];
        Country l_country;

        int l_count = 0;
        for (Map.Entry<String, Country> l_item : countries.entrySet()) {
            l_country = item.getValue();
            l_data[count] = populateRow(country, type);
            l_count++;
        }

        return format(l_columnNames, l_data);
    }

    private String[] populateRow(Country p_country, FormatType p_type) {
        ArrayList<String> l_values = new ArrayList<>();

        String l_neighborsAsCsv = String.join(",", p_country.getAdjacentCountries()
                .stream()
                .map(Country::getName)
                .collect(Collectors.toList()));

        l_values.add(p_country.getName());
        if (p_type == FormatType.DETAIL) {
            l_values.add(p_country.getNumberOfArmies() + "");
            l_values.add(p_country.getOwner() != null ? p_country.getOwner().getName() : "");
        }
        l_values.add(p_country.getContinent().getName() + " (" + p_country.getContinent().getValue() + ")");
        l_values.add(l_neighborsAsCsv);
        return l_values.toArray(new String[0]);
    }

    private String[] getColumnNames(FormatType p_type) {
        ArrayList<String> l_columns = new ArrayList<>();
        l_columns.add("Country");

        if (p_type == FormatType.DETAIL) {
            l_columns.add("Armies");
            l_columns.add("Owner");
        }
        l_columns.add("Continent (ControlValue)");
        l_columns.add("Adjacent Countries");
        return l_columns.toArray(new String[0]);
    }

    private String format(String[] p_columnNames, Object[][] p_data) {
        TextTable l_tt = new TextTable(p_columnNames, p_data);
        l_tt.setAddRowNumbering(false);
        // sort by the first column
        l_tt.setSort(0);
        return getTTString(l_tt, 0);
    }

    private String getTTString(TextTable p_tt, int p_indent) {
        final ByteArrayOutputStream l_baos = new ByteArrayOutputStream();
        try (PrintStream l_ps = new PrintStream(l_baos, true, "UTF-8")) {
            p_tt.printTable(l_ps, p_indent);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String l_data = new String(l_baos.toByteArray(), StandardCharsets.UTF_8);
        return l_data;
    }

    public enum FormatType {
        DEFAULT,
        DETAIL
    }
}
