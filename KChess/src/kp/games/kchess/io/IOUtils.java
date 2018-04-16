/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.games.kchess.io;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UTFDataFormatException;

/**
 *
 * @author Asus
 */
public final class IOUtils
{
    private IOUtils() {}
    
    public static final void writeSignedByte(byte[] buffer, int offset, byte b) { writeUnsignedByte(buffer, offset, b); }
    public static final void writeUnsignedByte(byte[] buffer, int offset, int b) { buffer[offset] = (byte) (b & 0xff); }
    public static final void writeSignedByte(OutputStream os, byte b) throws IOException { writeUnsignedByte(os, b); }
    public static final void writeUnsignedByte(OutputStream os, int b) throws IOException { os.write(b); }
    
    public static final void writeSignedInt16(byte[] buffer, int offset, short s) { writeUnsignedInt16(buffer, offset, s); }
    public static final void writeUnsignedInt16(byte[] buffer, int offset, int s)
    {
        buffer[offset + 1] = (byte) ((s >>> 8) & 0xff);
        buffer[offset] = (byte) (s & 0xff);
    }
    public static final void writeSignedInt16B(byte[] buffer, int offset, short s) { writeUnsignedInt16B(buffer, offset, s); }
    public static final void writeUnsignedInt16B(byte[] buffer, int offset, int s)
    {
        buffer[offset] = (byte) ((s >>> 8) & 0xff);
        buffer[offset + 1] = (byte) (s & 0xff);
    }
    public static final void writeSignedInt16(OutputStream os, short s) throws IOException { writeUnsignedInt16(os, s); }
    public static final void writeUnsignedInt16(OutputStream os, int s) throws IOException
    {
        byte[] b = new byte[2];
        writeUnsignedInt16(b, 0, 2);
        os.write(b, 0, 2);
    }
    public static final void writeSignedInt16B(OutputStream os, short s) throws IOException { writeUnsignedInt16B(os, s); }
    public static final void writeUnsignedInt16B(OutputStream os, int s) throws IOException
    {
        byte[] b = new byte[2];
        writeUnsignedInt16B(b, 0, 2);
        os.write(b, 0, 2);
    }
    
    public static final void writeSignedInt32(byte[] buffer, int offset, int i)
    {
        buffer[offset + 3] = (byte) ((i >>> 24) & 0xff);
        buffer[offset + 2] = (byte) ((i >>> 16) & 0xff);
        buffer[offset + 1] = (byte) ((i >>> 8) & 0xff);
        buffer[offset] = (byte) (i & 0xff);
    }
    public static final void writeUnsignedInt32(byte[] buffer, int offset, long i)
    {
        buffer[offset + 3] = (byte) ((i >>> 24L) & 0xffL);
        buffer[offset + 2] = (byte) ((i >>> 16L) & 0xffL);
        buffer[offset + 1] = (byte) ((i >>> 8L) & 0xffL);
        buffer[offset] = (byte) (i & 0xffL);
    }
    public static final void writeSignedInt32B(byte[] buffer, int offset, int i)
    {
        buffer[offset] = (byte) ((i >>> 24) & 0xff);
        buffer[offset + 1] = (byte) ((i >>> 16) & 0xff);
        buffer[offset + 2] = (byte) ((i >>> 8) & 0xff);
        buffer[offset + 3] = (byte) (i & 0xff);
    }
    public static final void writeUnsignedInt32B(byte[] buffer, int offset, long i)
    {
        buffer[offset] = (byte) ((i >>> 24L) & 0xffL);
        buffer[offset + 1] = (byte) ((i >>> 16L) & 0xffL);
        buffer[offset + 2] = (byte) ((i >>> 8L) & 0xffL);
        buffer[offset + 3] = (byte) (i & 0xffL);
    }
    public static final void writeSignedInt32(OutputStream os, int i) throws IOException
    {
        byte[] b = new byte[4];
        writeSignedInt32(b, 0, 4);
        os.write(b, 0, 4);
    }
    public static final void writeUnsignedInt32(OutputStream os, long i) throws IOException
    {
        byte[] b = new byte[4];
        writeUnsignedInt32(b, 0, 4);
        os.write(b, 0, 4);
    }
    public static final void writeSignedInt32B(OutputStream os, int i) throws IOException
    {
        byte[] b = new byte[4];
        writeSignedInt32B(b, 0, 4);
        os.write(b, 0, 4);
    }
    public static final void writeUnsignedInt32B(OutputStream os, long i) throws IOException
    {
        byte[] b = new byte[4];
        writeUnsignedInt32B(b, 0, 4);
        os.write(b, 0, 4);
    }
    
    public static final void writeSignedInt64(byte[] buffer, int offset, long l)
    {
        buffer[offset + 7] = (byte) ((l >>> 56L) & 0xffL);
        buffer[offset + 6] = (byte) ((l >>> 48L) & 0xffL);
        buffer[offset + 5] = (byte) ((l >>> 40L) & 0xffL);
        buffer[offset + 4] = (byte) ((l >>> 32L) & 0xffL);
        buffer[offset + 3] = (byte) ((l >>> 24L) & 0xffL);
        buffer[offset + 2] = (byte) ((l >>> 16L) & 0xffL);
        buffer[offset + 1] = (byte) ((l >>> 8L) & 0xffL);
        buffer[offset] = (byte) (l & 0xffL);
    }
    public static final void writeSignedInt64B(byte[] buffer, int offset, long l)
    {
        buffer[offset] = (byte) ((l >>> 56L) & 0xffL);
        buffer[offset + 1] = (byte) ((l >>> 48L) & 0xffL);
        buffer[offset + 2] = (byte) ((l >>> 40L) & 0xffL);
        buffer[offset + 3] = (byte) ((l >>> 32L) & 0xffL);
        buffer[offset + 4] = (byte) ((l >>> 24L) & 0xffL);
        buffer[offset + 5] = (byte) ((l >>> 16L) & 0xffL);
        buffer[offset + 6] = (byte) ((l >>> 8L) & 0xffL);
        buffer[offset + 7] = (byte) (l & 0xffL);
    }
    public static final void writeSignedInt64(OutputStream os, long l) throws IOException
    {
        byte[] b = new byte[8];
        writeSignedInt64(b, 0, 8);
        os.write(b, 0, 8);
    }
    public static final void writeSignedInt64B(OutputStream os, long l) throws IOException
    {
        byte[] b = new byte[8];
        writeSignedInt64(b, 0, 8);
        os.write(b, 0, 8);
    }
    
    public static final int writeUTF(OutputStream out, String str) throws IOException
    {
        int strlen = str.length();
        int utflen = 0;
        int c, count = 0;

        /* use charAt instead of copying String to char array */
        for(int i = 0; i < strlen; i++)
        {
            c = str.charAt(i);
            if ((c >= 0x0001) && (c <= 0x007F)) {
                utflen++;
            } else if (c > 0x07FF) {
                utflen += 3;
            } else {
                utflen += 2;
            }
        }

        if(utflen > 65535)
            throw new UTFDataFormatException("encoded string too long: " + utflen + " bytes");

        byte[] bytearr = new byte[utflen + 2];

        bytearr[count++] = (byte) ((utflen >>> 8) & 0xFF);
        bytearr[count++] = (byte) ((utflen >>> 0) & 0xFF);

        int i=0;
        for (i=0; i<strlen; i++) {
           c = str.charAt(i);
           if (!((c >= 0x0001) && (c <= 0x007F))) break;
           bytearr[count++] = (byte) c;
        }

        for (;i < strlen; i++){
            c = str.charAt(i);
            if ((c >= 0x0001) && (c <= 0x007F)) {
                bytearr[count++] = (byte) c;

            } else if (c > 0x07FF) {
                bytearr[count++] = (byte) (0xE0 | ((c >> 12) & 0x0F));
                bytearr[count++] = (byte) (0x80 | ((c >>  6) & 0x3F));
                bytearr[count++] = (byte) (0x80 | ((c >>  0) & 0x3F));
            } else {
                bytearr[count++] = (byte) (0xC0 | ((c >>  6) & 0x1F));
                bytearr[count++] = (byte) (0x80 | ((c >>  0) & 0x3F));
            }
        }
        out.write(bytearr, 0, utflen+2);
        return utflen + 2;
    }
    
    
    
    
    public static final byte readSignedByte(byte[] buffer, int offset) { return buffer[offset]; }
    public static final short readUnsignedByte(byte[] buffer, int offset) { return (short) (buffer[offset] & 0xff); }
    public static final byte readSignedByte(InputStream is) throws IOException { return (byte) (is.read() & 0xff); }
    public static final short readUnsignedByte(InputStream is) throws IOException { return (short) (is.read() & 0xff); }
    
    public static final short readSignedInt16(byte[] buffer, int offset)
    {
        return (short) (((buffer[offset + 1] & 0xff) << 8) | (buffer[offset] & 0xff));
    }
    public static final int readUnsignedInt16(byte[] buffer, int offset)
    {
        return ((buffer[offset + 1] & 0xff) << 8) | (buffer[offset] & 0xff);
    }
    public static final short readSignedInt16B(byte[] buffer, int offset)
    {
        return (short) (((buffer[offset] & 0xff) << 8) | (buffer[offset + 1] & 0xff));
    }
    public static final int readUnsignedInt16B(byte[] buffer, int offset)
    {
        return ((buffer[offset] & 0xff) << 8) | (buffer[offset + 1] & 0xff);
    }
    public static final short readSignedInt16(InputStream is) throws IOException
    {
        return readSignedInt16(readFully(is, 2), 0);
    }
    public static final int readUnsignedInt16(InputStream is) throws IOException
    {
        return readUnsignedInt16(readFully(is, 2), 0);
    }
    public static final short readSignedInt16B(InputStream is) throws IOException
    {
        return readSignedInt16B(readFully(is, 2), 0);
    }
    public static final int readUnsignedInt16B(InputStream is) throws IOException
    {
        return readUnsignedInt16B(readFully(is, 2), 0);
    }
    
    public static final int readSignedInt32(byte[] buffer, int offset)
    {
        return ((buffer[offset + 3] & 0xff) << 24) |
                ((buffer[offset + 2] & 0xff) << 16) |
                ((buffer[offset + 1] & 0xff) << 8) |
                (buffer[offset] & 0xff);
    }
    public static final long readUnsignedInt32(byte[] buffer, int offset)
    {
        return ((buffer[offset + 3] & 0xffL) << 24L) |
                ((buffer[offset + 2] & 0xffL) << 16L) |
                ((buffer[offset + 1] & 0xffL) << 8L) |
                (buffer[offset] & 0xffL);
    }
    public static final int readSignedInt32B(byte[] buffer, int offset)
    {
        return ((buffer[offset] & 0xff) << 24) |
                ((buffer[offset + 1] & 0xff) << 16) |
                ((buffer[offset + 2] & 0xff) << 8) |
                (buffer[offset + 3] & 0xff);
    }
    public static final long readUnsignedInt32B(byte[] buffer, int offset)
    {
        return ((buffer[offset] & 0xffL) << 24L) |
                ((buffer[offset + 1] & 0xffL) << 16L) |
                ((buffer[offset + 2] & 0xffL) << 8L) |
                (buffer[offset + 3] & 0xffL);
    }
    public static final int readSignedInt32(InputStream is) throws IOException
    {
        return readSignedInt32(readFully(is, 4), 0);
    }
    public static final long readUnsignedInt32(InputStream is) throws IOException
    {
        return readUnsignedInt32(readFully(is, 4), 0);
    }
    public static final int readSignedInt32B(InputStream is) throws IOException
    {
        return readSignedInt32B(readFully(is, 4), 0);
    }
    public static final long readUnsignedInt32B(InputStream is) throws IOException
    {
        return readUnsignedInt32B(readFully(is, 4), 0);
    }
    
    public static final long readSignedInt64(byte[] buffer, int offset)
    {
        return ((buffer[offset + 7] & 0xffL) << 56L) |
                ((buffer[offset + 6] & 0xffL) << 48L) |
                ((buffer[offset + 5] & 0xffL) << 40L) |
                ((buffer[offset + 4] & 0xffL) << 32L) |
                ((buffer[offset + 3] & 0xffL) << 24L) |
                ((buffer[offset + 2] & 0xffL) << 16L) |
                ((buffer[offset + 1] & 0xffL) << 8L) |
                (buffer[offset] & 0xffL);
    }
    public static final long readSignedInt64B(byte[] buffer, int offset)
    {
        return ((buffer[offset] & 0xffL) << 56L) |
                ((buffer[offset + 1] & 0xffL) << 48L) |
                ((buffer[offset + 2] & 0xffL) << 40L) |
                ((buffer[offset + 3] & 0xffL) << 32L) |
                ((buffer[offset + 4] & 0xffL) << 24L) |
                ((buffer[offset + 5] & 0xffL) << 16L) |
                ((buffer[offset + 6] & 0xffL) << 8L) |
                (buffer[offset + 7] & 0xffL);
    }
    
    public static final long readSignedInt64(InputStream is) throws IOException
    {
        return readSignedInt64B(readFully(is, 8), 0);
    }
    public static final long readSignedInt64B(InputStream is) throws IOException
    {
        return readSignedInt64B(readFully(is, 8), 0);
    }
    
    public static final String readUTF(InputStream in) throws IOException
    {
        int utflen = readUnsignedInt16(in);
        byte[] bytearr = new byte[utflen];
        char[] chararr = new char[utflen];

        int c, char2, char3;
        int count = 0;
        int chararr_count=0;

        readFully(in, bytearr, 0, utflen);

        while(count < utflen)
        {
            c = (int) bytearr[count] & 0xff;
            if (c > 127) break;
            count++;
            chararr[chararr_count++]=(char)c;
        }

        while(count < utflen)
        {
            c = (int) bytearr[count] & 0xff;
            switch(c >> 4)
            {
                case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7:
                    /* 0xxxxxxx*/
                    count++;
                    chararr[chararr_count++]=(char)c;
                    break;
                case 12: case 13:
                    /* 110x xxxx   10xx xxxx*/
                    count += 2;
                    if (count > utflen)
                        throw new UTFDataFormatException(
                            "malformed input: partial character at end");
                    char2 = (int) bytearr[count-1];
                    if ((char2 & 0xC0) != 0x80)
                        throw new UTFDataFormatException(
                            "malformed input around byte " + count);
                    chararr[chararr_count++]=(char)(((c & 0x1F) << 6) |
                                                    (char2 & 0x3F));
                    break;
                case 14:
                    /* 1110 xxxx  10xx xxxx  10xx xxxx */
                    count += 3;
                    if (count > utflen)
                        throw new UTFDataFormatException(
                            "malformed input: partial character at end");
                    char2 = (int) bytearr[count-2];
                    char3 = (int) bytearr[count-1];
                    if (((char2 & 0xC0) != 0x80) || ((char3 & 0xC0) != 0x80))
                        throw new UTFDataFormatException(
                            "malformed input around byte " + (count-1));
                    chararr[chararr_count++]=(char)(((c     & 0x0F) << 12) |
                                                    ((char2 & 0x3F) << 6)  |
                                                    ((char3 & 0x3F) << 0));
                    break;
                default:
                    /* 10xx xxxx,  1111 xxxx */
                    throw new UTFDataFormatException(
                        "malformed input around byte " + count);
            }
        }
        // The number of chars produced may be less than utflen
        return new String(chararr, 0, chararr_count);
    }
    
    
    
    public static final void readFully(InputStream is, byte[] buffer, int offset, int length) throws IOException
    {
        if(length < 0)
            throw new IndexOutOfBoundsException();
        int n = 0;
        while(n < length)
        {
            int count = is.read(buffer, offset + n, length - n);
            if(count < 0)
                throw new EOFException();
            n += count;
        }
    }
    public static final void readFully(InputStream is, byte[] buffer) throws IOException { readFully(is, buffer, 0, buffer.length); }
    public static final byte[] readFully(InputStream is, int bufLen) throws IOException
    {
        ByteArrayOutputStream bout = new ByteArrayOutputStream(bufLen);
        byte[] buf = new byte[bufLen];
        int count = is.read(buf);         
        while (count > 0)
        {
            bout.write(buf, 0, count);
            count = is.read(buf);
        }
        return bout.toByteArray();
    }
    
    public final static long skip(InputStream is, long len) throws IOException { return is.skip(len); }

    public final static void skipFully(InputStream is, int n) throws IOException { readFully(is, new byte[n]); }
}
