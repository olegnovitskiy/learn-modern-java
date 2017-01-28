package org.sample;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StreamSamplesTest {
    @Test
    public void listHave5Elements() throws Exception {
        int actual = StreamSamples.count(Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4, 5)));
        assertEquals(5, actual);
    }

    @Test
    public void listHave2Elements() throws Exception {
        int actual = StreamSamples.count(Arrays.asList(Arrays.asList(10), Arrays.asList(20)));
        assertEquals(2, actual);
    }

    @Test
    public void listHave0Elements() throws Exception {
        int actual = StreamSamples.count(Arrays.asList(Arrays.asList()));
        assertEquals(0, actual);
    }

    @Test
    public void listHave5ElementsAfterMerge() throws Exception {
        List<Integer> actual = StreamSamples.convert(Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4, 5)));
        assertEquals(Arrays.asList(1,2,3,4,5), actual);
    }

    @Test
    public void listHave0ElementsAfterMerge() throws Exception {
        List<Integer> actual = StreamSamples.convert(Collections.emptyList());
        assertEquals(Collections.<Integer>emptyList(), actual);
    }
}
