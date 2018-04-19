package my.vaadin.app;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class FilmForm extends FormLayout {

	private TextField nazwa = new TextField("Tytuł");
	private TextField opis = new TextField("Opis filmu");
	private TextField czasTrwania = new TextField("Czas seansu");
	private NativeSelect<Sala> sala = new NativeSelect<>("Sala");
	private TextField limitWiekowy = new TextField("Limit Wiekowy");
	private Button save = new Button("Zapisz");
	private Button delete = new Button("Usuń");

	private TheaterService service = TheaterService.getInstance();
	private Film film;
	private MyUI myUI;
	private Binder<Film> binder = new Binder<>(Film.class);

	public FilmForm(MyUI myUI) {
		this.myUI = myUI;

		setSizeUndefined();
		HorizontalLayout buttons = new HorizontalLayout(save, delete);
		addComponents(nazwa, opis, czasTrwania, sala, limitWiekowy, buttons);

		sala.setItems(Sala.values());
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(KeyCode.ENTER);

		binder.bindInstanceFields(this);

		save.addClickListener(e -> this.save());
		delete.addClickListener(e -> this.delete());
	}

	public void setFilm(Film film) {
		this.film = film;
		binder.setBean(film);

		delete.setVisible(film.isPersisted());
		setVisible(true);
		nazwa.selectAll();
	}

	private void delete() {
		service.delete(film);
		myUI.updateList();
		setVisible(false);
	}

	private void save() {
		service.save(film);
		myUI.updateList();
		setVisible(false);
	}

}