package example.cashcard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;
@JsonTest
class CashCardJsonTest {
    @Autowired
    private JacksonTester<CashCard>json;
    @Test
    void cashCardSerializationTest() throws IOException {
        CashCard cashcard = new CashCard(11L, 12.43);
        assertThat(json.write(cashcard)).isStrictlyEqualToJson("expected.json");
        assertThat(json.write(cashcard)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(cashcard)).extractingJsonPathValue("@.id").isEqualTo(11);
        assertThat(json.write(cashcard)).hasJsonPathNumberValue("@.amount");
        assertThat(json.write(cashcard)).extractingJsonPathValue("@.amount").isEqualTo(12.43);
    }
    @Test
    void cashCardDeserializationTest() throws IOException {
        String expected = """
           {
               "id":79,
               "amount":193.45
           }
           """;
        assertThat(json.parse(expected)).isEqualTo(new CashCard(79L, 193.45));
        assertThat(json.parseObject(expected).id()).isEqualTo(79);
        assertThat(json.parseObject(expected).amount()).isEqualTo(193.45);
    }
}
