package my.example.kata.sequence;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class SubSequenceUtilsTest {


    @Test
    public void testCase_1_OK(){

    int a[]={1,2,3,4};
    int b[]={1,3,4};

        Assert.assertTrue(SubSequenceUtils.isSubSequence(a,b));
    }

    @Test
    public void testCase_2_OK(){

        int a[]={1,2,3,4};
        int b[]={2,4};

        Assert.assertTrue(SubSequenceUtils.isSubSequence(a,b));
    }

    @Test
    public void testCase_3_OK(){

        int a[]={5,1,22,25,6,-1,8,10};
        int b[]={1,6,-1,10};

        Assert.assertTrue(SubSequenceUtils.isSubSequence(a,b));
    }



    @Test
    public void testCase_4_NOK(){

        int a[]={1,2,3,4};
        int b[]={1,4,3};

        Assert.assertFalse(SubSequenceUtils.isSubSequence(a,b));
    }
}
