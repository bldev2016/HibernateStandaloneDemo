/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.samples.hibernate.standalone.demo.client;

import de.samples.hibernate.standalone.demo.model.Message;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;


/**
 *
 * - Derby database: 'HibernateDB'.
 * - Libraries (required): hibernate-entitymanager 
 * - Libraries (optional): hibernate-envers (for auditing)
 *
 * @author macuser
 */
public class HibernateClient {
    
    private static final Logger LOGGER = LogManager.getLogger(HibernateClient.class);

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HibernateDemoPU");
        EntityManager em = emf.createEntityManager();
        long messageId = createMessage(em);
        retrieveMessageAudits(em, messageId);
        em.close();
        emf.close();
    }

    private static long createMessage(EntityManager em) {

        em.getTransaction().begin();

        Message message = new Message();
        message.setText("Hello World2!");
        em.persist(message);

        em.getTransaction().commit();

        return message.getId();
    }

    private static void retrieveMessageAudits(EntityManager em, long messageId) {
        AuditReader auditReader = AuditReaderFactory.get(em);

        List<Number> revisionNumbers = auditReader.getRevisions(Message.class, messageId);
        revisionNumbers.forEach((rev) -> {
            Message message = auditReader.find(Message.class, messageId, rev);
            LOGGER.info("Message ["+message+"] at revision ["+rev+"].");
        });
    }

}
