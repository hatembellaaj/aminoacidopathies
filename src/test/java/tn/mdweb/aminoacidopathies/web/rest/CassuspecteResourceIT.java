package tn.mdweb.aminoacidopathies.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tn.mdweb.aminoacidopathies.IntegrationTest;
import tn.mdweb.aminoacidopathies.domain.Cassuspecte;
import tn.mdweb.aminoacidopathies.domain.enumeration.elienparente;
import tn.mdweb.aminoacidopathies.repository.CassuspecteRepository;
import tn.mdweb.aminoacidopathies.service.dto.CassuspecteDTO;
import tn.mdweb.aminoacidopathies.service.mapper.CassuspecteMapper;

/**
 * Integration tests for the {@link CassuspecteResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CassuspecteResourceIT {

    private static final elienparente DEFAULT_LIENPARENTE = elienparente.FRERE_SOEUR;
    private static final elienparente UPDATED_LIENPARENTE = elienparente.PERE_MERE;

    private static final String DEFAULT_LIENPARENTEAUTRE = "AAAAAAAAAA";
    private static final String UPDATED_LIENPARENTEAUTRE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_SIGNES_NEUROLOGIQUES = false;
    private static final Boolean UPDATED_SIGNES_NEUROLOGIQUES = true;

    private static final Boolean DEFAULT_TROUBLES_DE_LA_CONSCIENCE = false;
    private static final Boolean UPDATED_TROUBLES_DE_LA_CONSCIENCE = true;

    private static final Boolean DEFAULT_RETARD_PSYCHOMOTEUR = false;
    private static final Boolean UPDATED_RETARD_PSYCHOMOTEUR = true;

    private static final Boolean DEFAULT_RETARD_MENTAL = false;
    private static final Boolean UPDATED_RETARD_MENTAL = true;

    private static final Boolean DEFAULT_SIGNES_DU_SPECTRE_AUTISTIQUE = false;
    private static final Boolean UPDATED_SIGNES_DU_SPECTRE_AUTISTIQUE = true;

    private static final Boolean DEFAULT_EPILEPSIE = false;
    private static final Boolean UPDATED_EPILEPSIE = true;

    private static final Boolean DEFAULT_CRISE_PSEUDOPORPHYRIQUE = false;
    private static final Boolean UPDATED_CRISE_PSEUDOPORPHYRIQUE = true;

    private static final String DEFAULT_AUTRES_SIGNES_NEUROLOGIQUES = "AAAAAAAAAA";
    private static final String UPDATED_AUTRES_SIGNES_NEUROLOGIQUES = "BBBBBBBBBB";

    private static final Boolean DEFAULT_SIGNES_HEPATIQUES = false;
    private static final Boolean UPDATED_SIGNES_HEPATIQUES = true;

    private static final Boolean DEFAULT_ICTERE = false;
    private static final Boolean UPDATED_ICTERE = true;

    private static final Boolean DEFAULT_BALLONNEMENT = false;
    private static final Boolean UPDATED_BALLONNEMENT = true;

    private static final Boolean DEFAULT_SYNDROME_HEMORRAGIQUE = false;
    private static final Boolean UPDATED_SYNDROME_HEMORRAGIQUE = true;

    private static final String DEFAULT_AUTRES_SIGNES_HEPATIQUES = "AAAAAAAAAA";
    private static final String UPDATED_AUTRES_SIGNES_HEPATIQUES = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/cassuspectes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CassuspecteRepository cassuspecteRepository;

    @Autowired
    private CassuspecteMapper cassuspecteMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCassuspecteMockMvc;

    private Cassuspecte cassuspecte;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cassuspecte createEntity(EntityManager em) {
        Cassuspecte cassuspecte = new Cassuspecte()
            .lienparente(DEFAULT_LIENPARENTE)
            .lienparenteautre(DEFAULT_LIENPARENTEAUTRE)
            .signes_neurologiques(DEFAULT_SIGNES_NEUROLOGIQUES)
            .troubles_de_la_conscience(DEFAULT_TROUBLES_DE_LA_CONSCIENCE)
            .retard_psychomoteur(DEFAULT_RETARD_PSYCHOMOTEUR)
            .retard_mental(DEFAULT_RETARD_MENTAL)
            .signes_du_spectre_autistique(DEFAULT_SIGNES_DU_SPECTRE_AUTISTIQUE)
            .epilepsie(DEFAULT_EPILEPSIE)
            .crise_pseudoporphyrique(DEFAULT_CRISE_PSEUDOPORPHYRIQUE)
            .autres_signes_neurologiques(DEFAULT_AUTRES_SIGNES_NEUROLOGIQUES)
            .signes_hepatiques(DEFAULT_SIGNES_HEPATIQUES)
            .ictere(DEFAULT_ICTERE)
            .ballonnement(DEFAULT_BALLONNEMENT)
            .syndrome_hemorragique(DEFAULT_SYNDROME_HEMORRAGIQUE)
            .autres_signes_hepatiques(DEFAULT_AUTRES_SIGNES_HEPATIQUES);
        return cassuspecte;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cassuspecte createUpdatedEntity(EntityManager em) {
        Cassuspecte cassuspecte = new Cassuspecte()
            .lienparente(UPDATED_LIENPARENTE)
            .lienparenteautre(UPDATED_LIENPARENTEAUTRE)
            .signes_neurologiques(UPDATED_SIGNES_NEUROLOGIQUES)
            .troubles_de_la_conscience(UPDATED_TROUBLES_DE_LA_CONSCIENCE)
            .retard_psychomoteur(UPDATED_RETARD_PSYCHOMOTEUR)
            .retard_mental(UPDATED_RETARD_MENTAL)
            .signes_du_spectre_autistique(UPDATED_SIGNES_DU_SPECTRE_AUTISTIQUE)
            .epilepsie(UPDATED_EPILEPSIE)
            .crise_pseudoporphyrique(UPDATED_CRISE_PSEUDOPORPHYRIQUE)
            .autres_signes_neurologiques(UPDATED_AUTRES_SIGNES_NEUROLOGIQUES)
            .signes_hepatiques(UPDATED_SIGNES_HEPATIQUES)
            .ictere(UPDATED_ICTERE)
            .ballonnement(UPDATED_BALLONNEMENT)
            .syndrome_hemorragique(UPDATED_SYNDROME_HEMORRAGIQUE)
            .autres_signes_hepatiques(UPDATED_AUTRES_SIGNES_HEPATIQUES);
        return cassuspecte;
    }

    @BeforeEach
    public void initTest() {
        cassuspecte = createEntity(em);
    }

    @Test
    @Transactional
    void createCassuspecte() throws Exception {
        int databaseSizeBeforeCreate = cassuspecteRepository.findAll().size();
        // Create the Cassuspecte
        CassuspecteDTO cassuspecteDTO = cassuspecteMapper.toDto(cassuspecte);
        restCassuspecteMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cassuspecteDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Cassuspecte in the database
        List<Cassuspecte> cassuspecteList = cassuspecteRepository.findAll();
        assertThat(cassuspecteList).hasSize(databaseSizeBeforeCreate + 1);
        Cassuspecte testCassuspecte = cassuspecteList.get(cassuspecteList.size() - 1);
        assertThat(testCassuspecte.getLienparente()).isEqualTo(DEFAULT_LIENPARENTE);
        assertThat(testCassuspecte.getLienparenteautre()).isEqualTo(DEFAULT_LIENPARENTEAUTRE);
        assertThat(testCassuspecte.getSignes_neurologiques()).isEqualTo(DEFAULT_SIGNES_NEUROLOGIQUES);
        assertThat(testCassuspecte.getTroubles_de_la_conscience()).isEqualTo(DEFAULT_TROUBLES_DE_LA_CONSCIENCE);
        assertThat(testCassuspecte.getRetard_psychomoteur()).isEqualTo(DEFAULT_RETARD_PSYCHOMOTEUR);
        assertThat(testCassuspecte.getRetard_mental()).isEqualTo(DEFAULT_RETARD_MENTAL);
        assertThat(testCassuspecte.getSignes_du_spectre_autistique()).isEqualTo(DEFAULT_SIGNES_DU_SPECTRE_AUTISTIQUE);
        assertThat(testCassuspecte.getEpilepsie()).isEqualTo(DEFAULT_EPILEPSIE);
        assertThat(testCassuspecte.getCrise_pseudoporphyrique()).isEqualTo(DEFAULT_CRISE_PSEUDOPORPHYRIQUE);
        assertThat(testCassuspecte.getAutres_signes_neurologiques()).isEqualTo(DEFAULT_AUTRES_SIGNES_NEUROLOGIQUES);
        assertThat(testCassuspecte.getSignes_hepatiques()).isEqualTo(DEFAULT_SIGNES_HEPATIQUES);
        assertThat(testCassuspecte.getIctere()).isEqualTo(DEFAULT_ICTERE);
        assertThat(testCassuspecte.getBallonnement()).isEqualTo(DEFAULT_BALLONNEMENT);
        assertThat(testCassuspecte.getSyndrome_hemorragique()).isEqualTo(DEFAULT_SYNDROME_HEMORRAGIQUE);
        assertThat(testCassuspecte.getAutres_signes_hepatiques()).isEqualTo(DEFAULT_AUTRES_SIGNES_HEPATIQUES);
    }

    @Test
    @Transactional
    void createCassuspecteWithExistingId() throws Exception {
        // Create the Cassuspecte with an existing ID
        cassuspecte.setId(1L);
        CassuspecteDTO cassuspecteDTO = cassuspecteMapper.toDto(cassuspecte);

        int databaseSizeBeforeCreate = cassuspecteRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCassuspecteMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cassuspecteDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cassuspecte in the database
        List<Cassuspecte> cassuspecteList = cassuspecteRepository.findAll();
        assertThat(cassuspecteList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCassuspectes() throws Exception {
        // Initialize the database
        cassuspecteRepository.saveAndFlush(cassuspecte);

        // Get all the cassuspecteList
        restCassuspecteMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cassuspecte.getId().intValue())))
            .andExpect(jsonPath("$.[*].lienparente").value(hasItem(DEFAULT_LIENPARENTE.toString())))
            .andExpect(jsonPath("$.[*].lienparenteautre").value(hasItem(DEFAULT_LIENPARENTEAUTRE)))
            .andExpect(jsonPath("$.[*].signes_neurologiques").value(hasItem(DEFAULT_SIGNES_NEUROLOGIQUES.booleanValue())))
            .andExpect(jsonPath("$.[*].troubles_de_la_conscience").value(hasItem(DEFAULT_TROUBLES_DE_LA_CONSCIENCE.booleanValue())))
            .andExpect(jsonPath("$.[*].retard_psychomoteur").value(hasItem(DEFAULT_RETARD_PSYCHOMOTEUR.booleanValue())))
            .andExpect(jsonPath("$.[*].retard_mental").value(hasItem(DEFAULT_RETARD_MENTAL.booleanValue())))
            .andExpect(jsonPath("$.[*].signes_du_spectre_autistique").value(hasItem(DEFAULT_SIGNES_DU_SPECTRE_AUTISTIQUE.booleanValue())))
            .andExpect(jsonPath("$.[*].epilepsie").value(hasItem(DEFAULT_EPILEPSIE.booleanValue())))
            .andExpect(jsonPath("$.[*].crise_pseudoporphyrique").value(hasItem(DEFAULT_CRISE_PSEUDOPORPHYRIQUE.booleanValue())))
            .andExpect(jsonPath("$.[*].autres_signes_neurologiques").value(hasItem(DEFAULT_AUTRES_SIGNES_NEUROLOGIQUES)))
            .andExpect(jsonPath("$.[*].signes_hepatiques").value(hasItem(DEFAULT_SIGNES_HEPATIQUES.booleanValue())))
            .andExpect(jsonPath("$.[*].ictere").value(hasItem(DEFAULT_ICTERE.booleanValue())))
            .andExpect(jsonPath("$.[*].ballonnement").value(hasItem(DEFAULT_BALLONNEMENT.booleanValue())))
            .andExpect(jsonPath("$.[*].syndrome_hemorragique").value(hasItem(DEFAULT_SYNDROME_HEMORRAGIQUE.booleanValue())))
            .andExpect(jsonPath("$.[*].autres_signes_hepatiques").value(hasItem(DEFAULT_AUTRES_SIGNES_HEPATIQUES)));
    }

    @Test
    @Transactional
    void getCassuspecte() throws Exception {
        // Initialize the database
        cassuspecteRepository.saveAndFlush(cassuspecte);

        // Get the cassuspecte
        restCassuspecteMockMvc
            .perform(get(ENTITY_API_URL_ID, cassuspecte.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cassuspecte.getId().intValue()))
            .andExpect(jsonPath("$.lienparente").value(DEFAULT_LIENPARENTE.toString()))
            .andExpect(jsonPath("$.lienparenteautre").value(DEFAULT_LIENPARENTEAUTRE))
            .andExpect(jsonPath("$.signes_neurologiques").value(DEFAULT_SIGNES_NEUROLOGIQUES.booleanValue()))
            .andExpect(jsonPath("$.troubles_de_la_conscience").value(DEFAULT_TROUBLES_DE_LA_CONSCIENCE.booleanValue()))
            .andExpect(jsonPath("$.retard_psychomoteur").value(DEFAULT_RETARD_PSYCHOMOTEUR.booleanValue()))
            .andExpect(jsonPath("$.retard_mental").value(DEFAULT_RETARD_MENTAL.booleanValue()))
            .andExpect(jsonPath("$.signes_du_spectre_autistique").value(DEFAULT_SIGNES_DU_SPECTRE_AUTISTIQUE.booleanValue()))
            .andExpect(jsonPath("$.epilepsie").value(DEFAULT_EPILEPSIE.booleanValue()))
            .andExpect(jsonPath("$.crise_pseudoporphyrique").value(DEFAULT_CRISE_PSEUDOPORPHYRIQUE.booleanValue()))
            .andExpect(jsonPath("$.autres_signes_neurologiques").value(DEFAULT_AUTRES_SIGNES_NEUROLOGIQUES))
            .andExpect(jsonPath("$.signes_hepatiques").value(DEFAULT_SIGNES_HEPATIQUES.booleanValue()))
            .andExpect(jsonPath("$.ictere").value(DEFAULT_ICTERE.booleanValue()))
            .andExpect(jsonPath("$.ballonnement").value(DEFAULT_BALLONNEMENT.booleanValue()))
            .andExpect(jsonPath("$.syndrome_hemorragique").value(DEFAULT_SYNDROME_HEMORRAGIQUE.booleanValue()))
            .andExpect(jsonPath("$.autres_signes_hepatiques").value(DEFAULT_AUTRES_SIGNES_HEPATIQUES));
    }

    @Test
    @Transactional
    void getNonExistingCassuspecte() throws Exception {
        // Get the cassuspecte
        restCassuspecteMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCassuspecte() throws Exception {
        // Initialize the database
        cassuspecteRepository.saveAndFlush(cassuspecte);

        int databaseSizeBeforeUpdate = cassuspecteRepository.findAll().size();

        // Update the cassuspecte
        Cassuspecte updatedCassuspecte = cassuspecteRepository.findById(cassuspecte.getId()).get();
        // Disconnect from session so that the updates on updatedCassuspecte are not directly saved in db
        em.detach(updatedCassuspecte);
        updatedCassuspecte
            .lienparente(UPDATED_LIENPARENTE)
            .lienparenteautre(UPDATED_LIENPARENTEAUTRE)
            .signes_neurologiques(UPDATED_SIGNES_NEUROLOGIQUES)
            .troubles_de_la_conscience(UPDATED_TROUBLES_DE_LA_CONSCIENCE)
            .retard_psychomoteur(UPDATED_RETARD_PSYCHOMOTEUR)
            .retard_mental(UPDATED_RETARD_MENTAL)
            .signes_du_spectre_autistique(UPDATED_SIGNES_DU_SPECTRE_AUTISTIQUE)
            .epilepsie(UPDATED_EPILEPSIE)
            .crise_pseudoporphyrique(UPDATED_CRISE_PSEUDOPORPHYRIQUE)
            .autres_signes_neurologiques(UPDATED_AUTRES_SIGNES_NEUROLOGIQUES)
            .signes_hepatiques(UPDATED_SIGNES_HEPATIQUES)
            .ictere(UPDATED_ICTERE)
            .ballonnement(UPDATED_BALLONNEMENT)
            .syndrome_hemorragique(UPDATED_SYNDROME_HEMORRAGIQUE)
            .autres_signes_hepatiques(UPDATED_AUTRES_SIGNES_HEPATIQUES);
        CassuspecteDTO cassuspecteDTO = cassuspecteMapper.toDto(updatedCassuspecte);

        restCassuspecteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, cassuspecteDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(cassuspecteDTO))
            )
            .andExpect(status().isOk());

        // Validate the Cassuspecte in the database
        List<Cassuspecte> cassuspecteList = cassuspecteRepository.findAll();
        assertThat(cassuspecteList).hasSize(databaseSizeBeforeUpdate);
        Cassuspecte testCassuspecte = cassuspecteList.get(cassuspecteList.size() - 1);
        assertThat(testCassuspecte.getLienparente()).isEqualTo(UPDATED_LIENPARENTE);
        assertThat(testCassuspecte.getLienparenteautre()).isEqualTo(UPDATED_LIENPARENTEAUTRE);
        assertThat(testCassuspecte.getSignes_neurologiques()).isEqualTo(UPDATED_SIGNES_NEUROLOGIQUES);
        assertThat(testCassuspecte.getTroubles_de_la_conscience()).isEqualTo(UPDATED_TROUBLES_DE_LA_CONSCIENCE);
        assertThat(testCassuspecte.getRetard_psychomoteur()).isEqualTo(UPDATED_RETARD_PSYCHOMOTEUR);
        assertThat(testCassuspecte.getRetard_mental()).isEqualTo(UPDATED_RETARD_MENTAL);
        assertThat(testCassuspecte.getSignes_du_spectre_autistique()).isEqualTo(UPDATED_SIGNES_DU_SPECTRE_AUTISTIQUE);
        assertThat(testCassuspecte.getEpilepsie()).isEqualTo(UPDATED_EPILEPSIE);
        assertThat(testCassuspecte.getCrise_pseudoporphyrique()).isEqualTo(UPDATED_CRISE_PSEUDOPORPHYRIQUE);
        assertThat(testCassuspecte.getAutres_signes_neurologiques()).isEqualTo(UPDATED_AUTRES_SIGNES_NEUROLOGIQUES);
        assertThat(testCassuspecte.getSignes_hepatiques()).isEqualTo(UPDATED_SIGNES_HEPATIQUES);
        assertThat(testCassuspecte.getIctere()).isEqualTo(UPDATED_ICTERE);
        assertThat(testCassuspecte.getBallonnement()).isEqualTo(UPDATED_BALLONNEMENT);
        assertThat(testCassuspecte.getSyndrome_hemorragique()).isEqualTo(UPDATED_SYNDROME_HEMORRAGIQUE);
        assertThat(testCassuspecte.getAutres_signes_hepatiques()).isEqualTo(UPDATED_AUTRES_SIGNES_HEPATIQUES);
    }

    @Test
    @Transactional
    void putNonExistingCassuspecte() throws Exception {
        int databaseSizeBeforeUpdate = cassuspecteRepository.findAll().size();
        cassuspecte.setId(count.incrementAndGet());

        // Create the Cassuspecte
        CassuspecteDTO cassuspecteDTO = cassuspecteMapper.toDto(cassuspecte);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCassuspecteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, cassuspecteDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(cassuspecteDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cassuspecte in the database
        List<Cassuspecte> cassuspecteList = cassuspecteRepository.findAll();
        assertThat(cassuspecteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCassuspecte() throws Exception {
        int databaseSizeBeforeUpdate = cassuspecteRepository.findAll().size();
        cassuspecte.setId(count.incrementAndGet());

        // Create the Cassuspecte
        CassuspecteDTO cassuspecteDTO = cassuspecteMapper.toDto(cassuspecte);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCassuspecteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(cassuspecteDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cassuspecte in the database
        List<Cassuspecte> cassuspecteList = cassuspecteRepository.findAll();
        assertThat(cassuspecteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCassuspecte() throws Exception {
        int databaseSizeBeforeUpdate = cassuspecteRepository.findAll().size();
        cassuspecte.setId(count.incrementAndGet());

        // Create the Cassuspecte
        CassuspecteDTO cassuspecteDTO = cassuspecteMapper.toDto(cassuspecte);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCassuspecteMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(cassuspecteDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Cassuspecte in the database
        List<Cassuspecte> cassuspecteList = cassuspecteRepository.findAll();
        assertThat(cassuspecteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCassuspecteWithPatch() throws Exception {
        // Initialize the database
        cassuspecteRepository.saveAndFlush(cassuspecte);

        int databaseSizeBeforeUpdate = cassuspecteRepository.findAll().size();

        // Update the cassuspecte using partial update
        Cassuspecte partialUpdatedCassuspecte = new Cassuspecte();
        partialUpdatedCassuspecte.setId(cassuspecte.getId());

        partialUpdatedCassuspecte
            .lienparente(UPDATED_LIENPARENTE)
            .lienparenteautre(UPDATED_LIENPARENTEAUTRE)
            .signes_neurologiques(UPDATED_SIGNES_NEUROLOGIQUES)
            .retard_mental(UPDATED_RETARD_MENTAL)
            .epilepsie(UPDATED_EPILEPSIE)
            .crise_pseudoporphyrique(UPDATED_CRISE_PSEUDOPORPHYRIQUE)
            .autres_signes_neurologiques(UPDATED_AUTRES_SIGNES_NEUROLOGIQUES)
            .signes_hepatiques(UPDATED_SIGNES_HEPATIQUES)
            .ballonnement(UPDATED_BALLONNEMENT)
            .autres_signes_hepatiques(UPDATED_AUTRES_SIGNES_HEPATIQUES);

        restCassuspecteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCassuspecte.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCassuspecte))
            )
            .andExpect(status().isOk());

        // Validate the Cassuspecte in the database
        List<Cassuspecte> cassuspecteList = cassuspecteRepository.findAll();
        assertThat(cassuspecteList).hasSize(databaseSizeBeforeUpdate);
        Cassuspecte testCassuspecte = cassuspecteList.get(cassuspecteList.size() - 1);
        assertThat(testCassuspecte.getLienparente()).isEqualTo(UPDATED_LIENPARENTE);
        assertThat(testCassuspecte.getLienparenteautre()).isEqualTo(UPDATED_LIENPARENTEAUTRE);
        assertThat(testCassuspecte.getSignes_neurologiques()).isEqualTo(UPDATED_SIGNES_NEUROLOGIQUES);
        assertThat(testCassuspecte.getTroubles_de_la_conscience()).isEqualTo(DEFAULT_TROUBLES_DE_LA_CONSCIENCE);
        assertThat(testCassuspecte.getRetard_psychomoteur()).isEqualTo(DEFAULT_RETARD_PSYCHOMOTEUR);
        assertThat(testCassuspecte.getRetard_mental()).isEqualTo(UPDATED_RETARD_MENTAL);
        assertThat(testCassuspecte.getSignes_du_spectre_autistique()).isEqualTo(DEFAULT_SIGNES_DU_SPECTRE_AUTISTIQUE);
        assertThat(testCassuspecte.getEpilepsie()).isEqualTo(UPDATED_EPILEPSIE);
        assertThat(testCassuspecte.getCrise_pseudoporphyrique()).isEqualTo(UPDATED_CRISE_PSEUDOPORPHYRIQUE);
        assertThat(testCassuspecte.getAutres_signes_neurologiques()).isEqualTo(UPDATED_AUTRES_SIGNES_NEUROLOGIQUES);
        assertThat(testCassuspecte.getSignes_hepatiques()).isEqualTo(UPDATED_SIGNES_HEPATIQUES);
        assertThat(testCassuspecte.getIctere()).isEqualTo(DEFAULT_ICTERE);
        assertThat(testCassuspecte.getBallonnement()).isEqualTo(UPDATED_BALLONNEMENT);
        assertThat(testCassuspecte.getSyndrome_hemorragique()).isEqualTo(DEFAULT_SYNDROME_HEMORRAGIQUE);
        assertThat(testCassuspecte.getAutres_signes_hepatiques()).isEqualTo(UPDATED_AUTRES_SIGNES_HEPATIQUES);
    }

    @Test
    @Transactional
    void fullUpdateCassuspecteWithPatch() throws Exception {
        // Initialize the database
        cassuspecteRepository.saveAndFlush(cassuspecte);

        int databaseSizeBeforeUpdate = cassuspecteRepository.findAll().size();

        // Update the cassuspecte using partial update
        Cassuspecte partialUpdatedCassuspecte = new Cassuspecte();
        partialUpdatedCassuspecte.setId(cassuspecte.getId());

        partialUpdatedCassuspecte
            .lienparente(UPDATED_LIENPARENTE)
            .lienparenteautre(UPDATED_LIENPARENTEAUTRE)
            .signes_neurologiques(UPDATED_SIGNES_NEUROLOGIQUES)
            .troubles_de_la_conscience(UPDATED_TROUBLES_DE_LA_CONSCIENCE)
            .retard_psychomoteur(UPDATED_RETARD_PSYCHOMOTEUR)
            .retard_mental(UPDATED_RETARD_MENTAL)
            .signes_du_spectre_autistique(UPDATED_SIGNES_DU_SPECTRE_AUTISTIQUE)
            .epilepsie(UPDATED_EPILEPSIE)
            .crise_pseudoporphyrique(UPDATED_CRISE_PSEUDOPORPHYRIQUE)
            .autres_signes_neurologiques(UPDATED_AUTRES_SIGNES_NEUROLOGIQUES)
            .signes_hepatiques(UPDATED_SIGNES_HEPATIQUES)
            .ictere(UPDATED_ICTERE)
            .ballonnement(UPDATED_BALLONNEMENT)
            .syndrome_hemorragique(UPDATED_SYNDROME_HEMORRAGIQUE)
            .autres_signes_hepatiques(UPDATED_AUTRES_SIGNES_HEPATIQUES);

        restCassuspecteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCassuspecte.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCassuspecte))
            )
            .andExpect(status().isOk());

        // Validate the Cassuspecte in the database
        List<Cassuspecte> cassuspecteList = cassuspecteRepository.findAll();
        assertThat(cassuspecteList).hasSize(databaseSizeBeforeUpdate);
        Cassuspecte testCassuspecte = cassuspecteList.get(cassuspecteList.size() - 1);
        assertThat(testCassuspecte.getLienparente()).isEqualTo(UPDATED_LIENPARENTE);
        assertThat(testCassuspecte.getLienparenteautre()).isEqualTo(UPDATED_LIENPARENTEAUTRE);
        assertThat(testCassuspecte.getSignes_neurologiques()).isEqualTo(UPDATED_SIGNES_NEUROLOGIQUES);
        assertThat(testCassuspecte.getTroubles_de_la_conscience()).isEqualTo(UPDATED_TROUBLES_DE_LA_CONSCIENCE);
        assertThat(testCassuspecte.getRetard_psychomoteur()).isEqualTo(UPDATED_RETARD_PSYCHOMOTEUR);
        assertThat(testCassuspecte.getRetard_mental()).isEqualTo(UPDATED_RETARD_MENTAL);
        assertThat(testCassuspecte.getSignes_du_spectre_autistique()).isEqualTo(UPDATED_SIGNES_DU_SPECTRE_AUTISTIQUE);
        assertThat(testCassuspecte.getEpilepsie()).isEqualTo(UPDATED_EPILEPSIE);
        assertThat(testCassuspecte.getCrise_pseudoporphyrique()).isEqualTo(UPDATED_CRISE_PSEUDOPORPHYRIQUE);
        assertThat(testCassuspecte.getAutres_signes_neurologiques()).isEqualTo(UPDATED_AUTRES_SIGNES_NEUROLOGIQUES);
        assertThat(testCassuspecte.getSignes_hepatiques()).isEqualTo(UPDATED_SIGNES_HEPATIQUES);
        assertThat(testCassuspecte.getIctere()).isEqualTo(UPDATED_ICTERE);
        assertThat(testCassuspecte.getBallonnement()).isEqualTo(UPDATED_BALLONNEMENT);
        assertThat(testCassuspecte.getSyndrome_hemorragique()).isEqualTo(UPDATED_SYNDROME_HEMORRAGIQUE);
        assertThat(testCassuspecte.getAutres_signes_hepatiques()).isEqualTo(UPDATED_AUTRES_SIGNES_HEPATIQUES);
    }

    @Test
    @Transactional
    void patchNonExistingCassuspecte() throws Exception {
        int databaseSizeBeforeUpdate = cassuspecteRepository.findAll().size();
        cassuspecte.setId(count.incrementAndGet());

        // Create the Cassuspecte
        CassuspecteDTO cassuspecteDTO = cassuspecteMapper.toDto(cassuspecte);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCassuspecteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, cassuspecteDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(cassuspecteDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cassuspecte in the database
        List<Cassuspecte> cassuspecteList = cassuspecteRepository.findAll();
        assertThat(cassuspecteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCassuspecte() throws Exception {
        int databaseSizeBeforeUpdate = cassuspecteRepository.findAll().size();
        cassuspecte.setId(count.incrementAndGet());

        // Create the Cassuspecte
        CassuspecteDTO cassuspecteDTO = cassuspecteMapper.toDto(cassuspecte);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCassuspecteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(cassuspecteDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cassuspecte in the database
        List<Cassuspecte> cassuspecteList = cassuspecteRepository.findAll();
        assertThat(cassuspecteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCassuspecte() throws Exception {
        int databaseSizeBeforeUpdate = cassuspecteRepository.findAll().size();
        cassuspecte.setId(count.incrementAndGet());

        // Create the Cassuspecte
        CassuspecteDTO cassuspecteDTO = cassuspecteMapper.toDto(cassuspecte);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCassuspecteMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(cassuspecteDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Cassuspecte in the database
        List<Cassuspecte> cassuspecteList = cassuspecteRepository.findAll();
        assertThat(cassuspecteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCassuspecte() throws Exception {
        // Initialize the database
        cassuspecteRepository.saveAndFlush(cassuspecte);

        int databaseSizeBeforeDelete = cassuspecteRepository.findAll().size();

        // Delete the cassuspecte
        restCassuspecteMockMvc
            .perform(delete(ENTITY_API_URL_ID, cassuspecte.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Cassuspecte> cassuspecteList = cassuspecteRepository.findAll();
        assertThat(cassuspecteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}