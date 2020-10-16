# How to use hg-revision plugin
1. Add plugin to pom.xml (to plugins section)

            <plugin>
                <groupId>com.github.volodya-lombrozo</groupId>
                <artifactId>hg-revision-plugin</artifactId>
                <version>0.3</version>
                <executions>
                    <execution>
                        <phase>
                            validate
                        </phase>
                        <goals>
                            <goal>scan</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>

2. Then, after validate phase you can use next maven properties:

      * hg.author - _last commit author_
      * hg.tags - _tags attached to last commit_
      * hg.branch - _current branch name_
      * hg.date - _time of last commit_ 
      * hg.desc - _description (message) of last commit_
      * hg.rev -  _last commit number_
      * hg.node - _last commit hash_
      * hg.bookmarks - _bookmarks attached to last commit_

3. Example project

    https://github.com/volodya-lombrozo/hg-revision-plugin-example
