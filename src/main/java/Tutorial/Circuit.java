package Tutorial;

import java.util.ArrayList;

/**
 * Created by Gideon on 4/28/15.
 */
public class Circuit{

    private ArrayList<Step> steps;
    public Step currentStep;


    /**
     *
     * @return true if if all the steps up to including @currentStep are valid
     */
    public boolean isTemporaryValid(){

        for(Step step : steps){
            if(step.equals(currentStep)){
                if(!step.isValid())
                    return false;
                else
                    return true;
            }
            else if(!step.isValid()) // an earlier step
                return false;
        }

        return false;
    }

    /**
     *
     * @return true if all steps are valid
     */
    public boolean isFinalized(){
        for(Step step : steps){
            if(!step.isValid())
                return false;
        }
        return true;
    }



    public ArrayList<Step> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<Step> steps) {
        this.steps = steps;
    }

    public Step getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(Step currentStep) {
        this.currentStep = currentStep;
    }
}
