package my.example.kata.sequence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class SubSequenceUtils {
    private static final Logger logger = LoggerFactory.getLogger(SubSequenceUtils.class);
    private SubSequenceUtils() {
            throw new IllegalStateException("Utility class");
        }


     public final static boolean isSubSequence(int [] A, int[] B){

        List<Integer> as=Arrays.stream(A).boxed().collect(Collectors.toList());
        List<Integer> bs=Arrays.stream(B).boxed().collect(Collectors.toList());;

         List<Integer> subAs=as;
        if(as.isEmpty()|| bs.isEmpty()){
            return false;
        }

        int index=0;
        int i=0;
         logger.info("AList={}",as);
         logger.info("BList={}",bs);
            while(subAs.size()>1 && index>-1 && i<bs.size()){
                subAs=subAs.subList(i,subAs.size());
                logger.info("subSequence={}",subAs);
                logger.info("element={}",bs.get(i));
                index=indexInSubSequence(bs.get(i++),subAs);
                logger.info("index={}",index);
            }
            return index>-1;

     }


private static int indexInSubSequence(int x,List<Integer> subSequence){
        return subSequence.indexOf(x);
}

}
