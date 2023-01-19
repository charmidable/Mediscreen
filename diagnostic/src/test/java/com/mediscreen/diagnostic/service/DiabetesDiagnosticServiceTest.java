package com.mediscreen.diagnostic.service;

import com.mediscreen.diagnostic.model.Patient;
import com.mediscreen.diagnostic.proxy.NoteProxy;
import com.mediscreen.diagnostic.proxy.PatientProxy;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.stream.Stream;
import static java.time.LocalDate.of;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.mediscreen.diagnostic.model.DiabetesRiskLevel;

@ExtendWith(MockitoExtension.class)
class DiabetesDiagnosticServiceTest
{
    @InjectMocks
    DiabetesDiagnosticService service;

    @Mock
    NoteProxy noteProxy;

    @Mock
    PatientProxy patientProxy;


    @ParameterizedTest
    @ArgumentsSource(DiagnosedArgProvider.class)
    void getDiagnosedPatientById(Argument arg)
    {
        when(patientProxy.getPatientById(any())).thenReturn(arg.patient());

        when(noteProxy.countTermInNotesByPatientId(any(), any())).thenReturn(arg.numberOfSymptome());

        assertThat( service.getDiagnosedPatientById(1).diabetesRiskLevel())
                .describedAs(
                        arg.patient().sex()
                                + (currentYear - arg.patient.birthdate().getYear() > 30 ? " more" : " less")
                                +  " than 30 years with "
                                + arg.numberOfSymptome
                                + " symptoms should be diagnosed "
                                + arg.riskLeveExpected()
                ).isEqualTo(arg.riskLeveExpected);
    }


    record Argument(Patient patient, int numberOfSymptome, String riskLeveExpected){}

    static int currentYear = LocalDate.now().getYear();

    static class DiagnosedArgProvider implements ArgumentsProvider
    {
        static LocalDate dateLess30 = of(currentYear - 20,1,1);
        static LocalDate dateMore30 = of(currentYear - 40,1,1);

        static Patient maleLess30 = new Patient(null, null,null, dateLess30, "male", null, null, null, null);
        static Patient maleMore30 = new Patient(null, null,null, dateMore30, "male", null, null, null, null);
        static Patient femaleLess30 = new Patient(null, null,null, dateLess30, "female", null, null, null, null);
        static Patient femaleMore30 = new Patient(null, null,null, dateMore30, "female", null, null, null, null);

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context)
        {
            return Stream.of(
                                Arguments.of(new Argument(maleLess30, 0, DiabetesRiskLevel.NONE.toString())),
                                Arguments.of(new Argument(maleLess30, 1, DiabetesRiskLevel.NONE.toString())),
                                Arguments.of(new Argument(maleLess30, 2, DiabetesRiskLevel.NONE.toString())),
                                Arguments.of(new Argument(maleLess30, 3, DiabetesRiskLevel.IN_DANGER.toString())),
                                Arguments.of(new Argument(maleLess30, 4, DiabetesRiskLevel.IN_DANGER.toString())),
                                Arguments.of(new Argument(maleLess30, 5, DiabetesRiskLevel.EARLY_ONSET.toString())),
                                Arguments.of(new Argument(maleLess30, 6, DiabetesRiskLevel.EARLY_ONSET.toString())),
                                Arguments.of(new Argument(maleLess30, 9, DiabetesRiskLevel.EARLY_ONSET.toString())),

                                Arguments.of(new Argument(maleMore30, 0, DiabetesRiskLevel.NONE.toString())),
                                Arguments.of(new Argument(maleMore30, 1, DiabetesRiskLevel.NONE.toString())),
                                Arguments.of(new Argument(maleMore30, 2, DiabetesRiskLevel.BORDERLINE.toString())),
                                Arguments.of(new Argument(maleMore30, 3, DiabetesRiskLevel.BORDERLINE.toString())),
                                Arguments.of(new Argument(maleMore30, 4, DiabetesRiskLevel.BORDERLINE.toString())),
                                Arguments.of(new Argument(maleMore30, 5, DiabetesRiskLevel.BORDERLINE.toString())),
                                Arguments.of(new Argument(maleMore30, 6, DiabetesRiskLevel.IN_DANGER.toString())),
                                Arguments.of(new Argument(maleMore30, 9, DiabetesRiskLevel.IN_DANGER.toString())),

                                Arguments.of(new Argument(femaleLess30, 0, DiabetesRiskLevel.NONE.toString())),
                                Arguments.of(new Argument(femaleLess30, 1, DiabetesRiskLevel.NONE.toString())),
                                Arguments.of(new Argument(femaleLess30, 2, DiabetesRiskLevel.NONE.toString())),
                                Arguments.of(new Argument(femaleLess30, 3, DiabetesRiskLevel.BORDERLINE.toString())),
                                Arguments.of(new Argument(femaleLess30, 4, DiabetesRiskLevel.IN_DANGER.toString())),
                                Arguments.of(new Argument(femaleLess30, 5, DiabetesRiskLevel.IN_DANGER.toString())),
                                Arguments.of(new Argument(femaleLess30, 6, DiabetesRiskLevel.IN_DANGER.toString())),
                                Arguments.of(new Argument(femaleLess30, 9, DiabetesRiskLevel.EARLY_ONSET.toString())),

                                Arguments.of(new Argument(femaleMore30, 0, DiabetesRiskLevel.NONE.toString())),
                                Arguments.of(new Argument(femaleMore30, 1, DiabetesRiskLevel.NONE.toString())),
                                Arguments.of(new Argument(femaleMore30, 2, DiabetesRiskLevel.BORDERLINE.toString())),
                                Arguments.of(new Argument(femaleMore30, 3, DiabetesRiskLevel.BORDERLINE.toString())),
                                Arguments.of(new Argument(femaleMore30, 4, DiabetesRiskLevel.BORDERLINE.toString())),
                                Arguments.of(new Argument(femaleMore30, 5, DiabetesRiskLevel.BORDERLINE.toString())),
                                Arguments.of(new Argument(femaleMore30, 6, DiabetesRiskLevel.IN_DANGER.toString())),
                                Arguments.of(new Argument(femaleMore30, 9, DiabetesRiskLevel.IN_DANGER.toString()))
                            );
        }
    }
}