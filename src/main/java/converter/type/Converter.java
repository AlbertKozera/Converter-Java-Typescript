package converter.type;

public interface Converter {
    void nextConverter(Converter nextConverter);

    StringBuilder convert(String code);
}
