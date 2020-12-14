package domain.repo;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CommandLineRepository implements Repository {
    @Override
    public List<String> bookmarks() {
        return null;
    }

    @Override
    public Changeset currentChangeset() {
        /*
         * Command for getting of current changeset
         * Tested on Windows OS only
         *
         * hg log -r . --template "user:{author}\nbranch:{branch}\ndate:{date}\nmessage:{desc}\nnode:{node}\nrev:{rev}\ntags:{tags}\nparents:{parents}"
         *
         */

        ProcessBuilder processBuilder = new ProcessBuilder("hg", "log", "-r", ".", "--template", "\"user:{author}\nbranch:{branch}\ndate:{date}\nmessage:{desc}\nnode:{node}\nrev:{rev}\ntags:{tags}\nparents:{parents}\"");
        try {
            processBuilder.directory(Paths.get("D:\\workspace\\hg_repo").toFile());
            Process process = processBuilder.start();
            InputStream is = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            reader.lines().forEach(System.out::println);
            reader.close();
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                br.lines().forEach(System.out::println);
                br.close();
            }
            System.out.println("Exit code: " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
