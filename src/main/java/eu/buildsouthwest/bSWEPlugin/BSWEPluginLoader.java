package eu.buildsouthwest.bSWEPlugin;

import io.papermc.paper.plugin.loader.PluginClasspathBuilder;
import io.papermc.paper.plugin.loader.PluginLoader;
import io.papermc.paper.plugin.loader.library.impl.MavenLibraryResolver;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.repository.RemoteRepository;

//public class BSWEPluginLoader implements PluginLoader {
//    @Override
//    public void classloader(PluginClasspathBuilder classpathBuilder){
//        MavenLibraryResolver resolver = new MavenLibraryResolver();
//        resolver.addRepository(new RemoteRepository.Builder(
//                "bteReleases", "default", "https://maven.buildtheearth.net/releases"
//        ).build());
//        resolver.addDependency(new Dependency(
//                new DefaultArtifact("net.buildtheearth.terraminusminus:terraminusminus-core:2.2.1-1.21.8"), null
//        ));
//        classpathBuilder.addLibrary(resolver);
//    }
//}
