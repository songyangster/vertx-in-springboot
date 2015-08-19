package misc.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Foo implements Serializable {
    private String string;
    private transient String s2;
    private transient JsonNode jsonNode;

    public Foo(String string, String s2, JsonNode jsonNode) {
        this.string = string;
        this.s2 = s2;
        this.jsonNode = jsonNode;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(s2);
        if (this.jsonNode != null) (new ObjectMapper()).writeValue(out, jsonNode);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        s2 = (String) in.readObject();
        if (in.available()>0) this.jsonNode = (new ObjectMapper()).readValue(in, JsonNode.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Foo)) return false;

        Foo foo = (Foo) o;

        if (string != null ? !string.equals(foo.string) : foo.string != null) return false;
        if (s2 != null ? !s2.equals(foo.s2) : foo.s2 != null) return false;
        return !(jsonNode != null ? !jsonNode.equals(foo.jsonNode) : foo.jsonNode != null);

    }

    @Override
    public int hashCode() {
        int result = string != null ? string.hashCode() : 0;
        result = 31 * result + (s2 != null ? s2.hashCode() : 0);
        result = 31 * result + (jsonNode != null ? jsonNode.hashCode() : 0);
        return result;
    }
}
