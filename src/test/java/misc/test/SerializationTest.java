package misc.test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.Assert;

import java.io.*;

public class SerializationTest {
    @Test
    public void testSerialization() {
        JsonNode node = (new ObjectMapper()).valueToTree("Test");
        Foo foo = new Foo("Bar", node);

        String fileName = "foo.ser";
        try (
                OutputStream file = new FileOutputStream(fileName);
                OutputStream buffer = new BufferedOutputStream(file);
                ObjectOutput output = new ObjectOutputStream(buffer);
        ){
            output.writeObject(foo);
        }
        catch(IOException ex){
            ex.getStackTrace();
        }

        Foo fooNew = null;

        //deserialize the ser file
        try(
                InputStream file = new FileInputStream(fileName);
                InputStream buffer = new BufferedInputStream(file);
                ObjectInput input = new ObjectInputStream (buffer);
        ){
            //deserialize the Object
            fooNew = (Foo) input.readObject();
        }
        catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }
        catch(IOException ex){
            ex.printStackTrace();
        }

        Assert.assertEquals(foo, fooNew);
    }

}