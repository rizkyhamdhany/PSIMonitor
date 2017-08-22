package com.psi.monitor;

import android.support.test.runner.AndroidJUnit4;

import com.psi.monitor.controllers.PSIController;
import com.psi.monitor.controllers.apidata.entities.PsiByDate;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

/**
 * Created by hamdhanywijaya@gmail.com on 8/20/17.
 */

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApiCallTest {

    @Test
    public void test1_shouldSuccessGetPSI() throws Exception {
        final TestResponse<PsiByDate> testResponse = new TestResponse<>();
        testResponse.setIdle(true);
        new PSIController(null){
            @Override
            public void onAPIsuccess() {
                testResponse.setData(data);
                testResponse.setIdle(false);
            }

            @Override
            public void onAPIFailed(String errorMessage) {
                testResponse.setErrorMessage(errorMessage);
                testResponse.setIdle(false);
            }
        }.execute();
        while (testResponse.isIdle()) {
            //wait request api until success/failed
        }
        Assert.assertNotNull(testResponse.getData()); // PSI data should be not null
        Assert.assertNull(testResponse.getErrorMessage()); // error message  should be null
        Assert.assertNotNull(testResponse.getData().getRegionMetadata()); // region metadata should be not null
        Assert.assertNotEquals(testResponse.getData().getRegionMetadata().size(), 0); // region metadata list should be more than 0
        Assert.assertNotNull(testResponse.getData().getItems()); // psi item should be not null
        Assert.assertNotEquals(testResponse.getData().getItems().size(), 0); // psiItem list should be more than 0
    }

    @Test
    public void test2_shouldSuccessGetPSIByDate() throws Exception {
        final TestResponse<PsiByDate> testResponse = new TestResponse<>();
        testResponse.setIdle(true);
        new PSIController("2017-06-20"){
            @Override
            public void onAPIsuccess() {
                testResponse.setData(data);
                testResponse.setIdle(false);
            }

            @Override
            public void onAPIFailed(String errorMessage) {
                testResponse.setErrorMessage(errorMessage);
                testResponse.setIdle(false);
            }
        }.execute();
        while (testResponse.isIdle()) {
            //wait request api until success/failed
        }
        Assert.assertNotNull(testResponse.getData()); // PSI data should be not null
        Assert.assertNull(testResponse.getErrorMessage()); // error message  should be null
        Assert.assertNotNull(testResponse.getData().getRegionMetadata()); // region metadata should be not null
        Assert.assertNotEquals(testResponse.getData().getRegionMetadata().size(), 0); // region metadata list should be more than 0
        Assert.assertNotNull(testResponse.getData().getItems()); // psi item should be not null
        Assert.assertNotEquals(testResponse.getData().getItems().size(), 0); // psiItem list should be more than 0
    }

    @Test
    public void test3_shouldFailedGetPSIIfDateFormatWrong() throws Exception {
        final TestResponse<PsiByDate> testResponse = new TestResponse<>();
        testResponse.setIdle(true);
        new PSIController("loremipsum"){
            @Override
            public void onAPIsuccess() {
                testResponse.setData(data);
                testResponse.setIdle(false);
            }

            @Override
            public void onAPIFailed(String errorMessage) {
                testResponse.setErrorMessage(errorMessage);
                testResponse.setIdle(false);
            }
        }.execute();
        while (testResponse.isIdle()) {
            //wait request api until success/failed
        }
        Assert.assertNotNull(testResponse.getErrorMessage()); // error message  should be not null
    }
}
