package model.response.addBookOldUser;

import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ResponseBodyAddBook {
	private List<BooksItem> books;
}