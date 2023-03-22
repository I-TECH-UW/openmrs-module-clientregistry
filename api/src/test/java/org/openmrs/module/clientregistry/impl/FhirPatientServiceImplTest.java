package org.openmrs.module.clientregistry.impl;

import ca.uhn.fhir.rest.api.server.IBundleProvider;
import ca.uhn.fhir.rest.param.TokenAndListParam;
import ca.uhn.fhir.rest.param.TokenParam;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Patient;
import ca.uhn.fhir.model.api.Include;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.ConceptService;
import org.openmrs.api.EncounterService;
import org.openmrs.api.ObsService;
import org.openmrs.module.fhir2.FhirConstants;
import org.openmrs.module.fhir2.api.dao.FhirPatientDao;
import org.openmrs.module.fhir2.api.search.SearchQueryInclude;
import org.openmrs.module.fhir2.api.search.param.SearchParameterMap;
import org.openmrs.module.fhir2.api.translators.PatientTranslator;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.collection.IsIterableWithSize.iterableWithSize;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author smallGod
 */
public class FhirPatientServiceImplTest extends BaseModuleContextSensitiveTest {

    @Autowired
    private EncounterService encounterService;

    @Autowired
    private ConceptService conceptService;

    @Autowired
    private ObsService obsService;

    private Method methodInvoked;


    private static final int START_INDEX = 0;

    private static final int END_INDEX = 10;

    @Autowired
    private PatientTranslator translator;

    @Autowired
    private FhirPatientDao dao;

    @Autowired
    private SearchQueryInclude<Patient> searchQueryInclude;

    @Before
    public void initialiseCommonTestData() throws Exception {


        executeDataSet("test-data/org.openmrs.module.clientregistry/api/api/dao/impl/FhirPatientDaoImplTest_initial_data.xml");
        updateSearchIndex();
        //Class encounterServiceImplClass = Class.forName("org.openmrs.api.impl.EncounterServiceImpl");
        //methodInvoked = encounterServiceImplClass.getDeclaredMethod(ConceptComputeTrigger.SAVE_ENCOUNTER, Encounter.class);
    }

    @Test
    public void searchForPatient_shouldReturnOnePatientinReturnedResults() {

        TokenAndListParam uuid = new TokenAndListParam().addAnd(new TokenParam("da7f524f-27ce-4bb2-86d6-6d1d05312bd5"));
        HashSet<Include> revIncludes = new HashSet<>();
//		revIncludes.add(new Include("MedicationRequest:patient"));
//		revIncludes.add(new Include("MedicationDispense:prescription", true));

        SearchParameterMap theParams = new SearchParameterMap()
                .addParameter(FhirConstants.COMMON_SEARCH_HANDLER, FhirConstants.ID_PROPERTY, uuid)
                .addParameter(FhirConstants.REVERSE_INCLUDE_SEARCH_HANDLER, revIncludes);

        IBundleProvider results = search(theParams);

        Assert.assertEquals(results, notNullValue());
        Assert.assertEquals(results.size(), equalTo(1));


        List<IBaseResource> resultList = get(results);

        Assert.assertEquals(results, notNullValue());

        assertThat(resultList.size(), equalTo(1));
        assertThat(resultList.stream().filter(result -> result instanceof Patient).collect(Collectors.toList()),
                is(iterableWithSize(1))); // the actual matched patient


//		assertThat(resultList.stream().filter(result -> result instanceof MedicationRequest).collect(Collectors.toList()),
//		    is(iterableWithSize(6))); // 6 requests that reference that patient (excluding discontinue orders)
//		assertThat(resultList.stream().filter(result -> result instanceof MedicationDispense).collect(Collectors.toList()),
//		    is(iterableWithSize(1))); // 1 dispense that references the above requests
    }
}
