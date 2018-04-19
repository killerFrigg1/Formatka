package my.vaadin.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TheaterService {

	private static TheaterService instance;
	private static final Logger LOGGER = Logger.getLogger(TheaterService.class.getName());

	private final HashMap<Long, Film> films = new HashMap<>();
	private long nextId = 0;

	private TheaterService() {
	}

	public static TheaterService getInstance() {
		if (instance == null) {
			instance = new TheaterService();
			instance.ensureTestData();
		}
		return instance;
	}

	public synchronized List<Film> findAll() {
		return findAll(null);
	}

	public synchronized List<Film> findAll(String stringFilter) {
		ArrayList<Film> arrayList = new ArrayList<>();
		for (Film film : films.values()) {
			try {
				boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| film.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(film.clone());
				}
			} catch (CloneNotSupportedException ex) {
				Logger.getLogger(TheaterService.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		Collections.sort(arrayList, new Comparator<Film>() {

			@Override
			public int compare(Film o1, Film o2) {
				return (int) (o2.getId() - o1.getId());
			}
		});
		return arrayList;
	}

	public synchronized List<Film> findAll(String stringFilter, int start, int maxresults) {
		ArrayList<Film> arrayList = new ArrayList<>();
		for (Film film : films.values()) {
			try {
				boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| film.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(film.clone());
				}
			} catch (CloneNotSupportedException ex) {
				Logger.getLogger(TheaterService.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		Collections.sort(arrayList, new Comparator<Film>() {

			@Override
			public int compare(Film o1, Film o2) {
				return (int) (o2.getId() - o1.getId());
			}
		});
		int end = start + maxresults;
		if (end > arrayList.size()) {
			end = arrayList.size();
		}
		return arrayList.subList(start, end);
	}

	public synchronized long count() {
		return films.size();
	}

	public synchronized void delete(Film value) {
		films.remove(value.getId());
	}

	public synchronized void save(Film entry) {
		if (entry == null) {
			LOGGER.log(Level.SEVERE,
					"Film is null. Are you sure you have connected your form to the application as described in tutorial chapter 7?");
			return;
		}
		if (entry.getId() == null) {
			entry.setId(nextId++);
		}
		try {
			entry = (Film) entry.clone();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		films.put(entry.getId(), entry);
	}

	public void ensureTestData() {
		if (findAll().isEmpty()) {
			final String[] names = new String[] { "The Matrix" };
			Random r = new Random(0);
			for (String name : names) {
				String[] split = name.split(";");
				Film f = new Film();
				f.setNazwa(split[0]);
				f.setOpis("Super film o SI.");
				f.setCzasTrwania("2 godz. 16 min.");
				f.setLimitWiekowy("R");
				f.setSala(Sala.A);
				save(f);
			}
		}
	}

}