package payment.exception;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class BlockHandler {
    public static String handleException(BlockException ex){
        return "Now the server is full, please try again later.";
    }
}
