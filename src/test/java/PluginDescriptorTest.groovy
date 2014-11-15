import org.jenkinsci.plugins.emotional_mascot.EmotionalMascotDescriptor

/**
 * Created by Hi on 2014/11/15.
 */
class PluginDescriptorTest extends GroovyTestCase {
    void testInitializing() {
        assertNotNull new EmotionalMascotDescriptor()
    }
}
