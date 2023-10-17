package lowLevelDesign.designPattern.chainOfResponsibility;

// client
public class CustomLogger {
    public static void main(String[] args) {
        LogProcessor logger = new InfoLogProcessor(new DebugLogProcessor(new ErrorLogProcessor(null)));

        logger.log(LogProcessor.ERROR, "This is Error!");
        logger.log(LogProcessor.DEBUG, "This is Debug!");
        logger.log(LogProcessor.INFO, "This is Info!");
    }
}

// Here chaining is happening like LinkedList, where the LogProcessor obj has a self pointer
//
abstract class LogProcessor {
    public static final int INFO = 1;
    public static final int DEBUG = 2;
    public static final int ERROR = 3;
    LogProcessor nextLogProcessor;

    LogProcessor(LogProcessor next){
        this.nextLogProcessor = next;
    }

    public void log(int logLevel, String logMessage){
        if(nextLogProcessor != null){
            nextLogProcessor.log(logLevel, logMessage);
        }
    }
}

class InfoLogProcessor extends LogProcessor{
    InfoLogProcessor(LogProcessor next){
        super(next);
    }

    // if current log-level match the current class type then print msg
    // else, call nextLogProcessor.log() using super.log()
    // where the LogProcessor is set in constructor using super class constructor
    @Override
    public void log(int logLevel, String logMessage) {
        if(logLevel == INFO){
            System.out.println("INFO: "+ logMessage);
        }else{
            super.log(logLevel, logMessage);
        }
    }
}

class DebugLogProcessor extends LogProcessor{
    DebugLogProcessor(LogProcessor next){
        super(next);
    }

    @Override
    public void log(int logLevel, String logMessage) {
        if(logLevel == DEBUG){
            System.out.println("DEBUG: "+ logMessage);
        }else{
            super.log(logLevel, logMessage);
        }
    }
}

class ErrorLogProcessor extends LogProcessor{
    ErrorLogProcessor(LogProcessor next){
        super(next);
    }

    @Override
    public void log(int logLevel, String logMessage) {
        if(logLevel == ERROR){
            System.out.println("ERROR: "+ logMessage);
        }else{
            super.log(logLevel, logMessage);
        }
    }
}