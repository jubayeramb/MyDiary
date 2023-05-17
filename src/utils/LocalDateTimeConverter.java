// https://gist.github.com/ankushs92/b72f48e5ffd4c1f27ffcc3a3265dcb0a
package utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class LocalDateTimeConverter {
    public Timestamp convertToDatabaseColumn(LocalDateTime ldt) {
        return Timestamp.valueOf(ldt);
    }

    public LocalDateTime convertToEntityAttribute(Timestamp ts) {
        if (ts != null) {
            return ts.toLocalDateTime();
        }
        return null;
    }
}
