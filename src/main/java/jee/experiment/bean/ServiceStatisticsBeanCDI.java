package jee.experiment.bean;  

import javax.enterprise.context.ApplicationScoped;

import com.ericsson.oss.itpf.sdk.instrument.annotation.InstrumentedBean;

@ApplicationScoped
@InstrumentedBean
public class ServiceStatisticsBeanCDI {
	private int x;
    private int numberOfRequests;

    /**
     * @return the numberOfRequests
     */
    public int getNumberOfRequests() {
        return numberOfRequests;
    }

    /**
     * @param numberOfRequests
     *            the numberOfRequests to set
     */
    public void setNumberOfRequests(final int numberOfRequests) {
        this.numberOfRequests = numberOfRequests;
    }

}
