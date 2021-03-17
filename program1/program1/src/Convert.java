//convert data to byte arrays
//Source: http://www.java2s.com/Book/Java/Examples/Convert_data_to_byte_array_back_and_forth.htm

import java.nio.ByteBuffer;

public class Convert 
{
    Convert()
    {
    }

    public static float toFloat(byte[] array)
    {
        ByteBuffer buffer = ByteBuffer.wrap(array);
        return buffer.getFloat();
    }

    public static int toInt(byte[] array)
    {
        ByteBuffer buffer = ByteBuffer.wrap(array);
        return buffer.getInt();
    }
}