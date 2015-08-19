package misc.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Foo implements Serializable {
    private String string;
    private transient JsonNode jsonNode;

    public Foo(String string, JsonNode jsonNode) {
        this.string = string;
        this.jsonNode = jsonNode;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        if (this.jsonNode != null) (new ObjectMapper()).writeValue(out, jsonNode);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.jsonNode = (new ObjectMapper()).readValue(in, JsonNode.class);
    }
}
