# How to use hg-revision plugin
1. Add plugin to pom.xml (to plugins section)

            <plugin>
                <groupId>com.github.volodya-lombrozo</groupId>
                <artifactId>hg-revision-plugin</artifactId>
                <version>0.6</version>
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

      * hg.author - _current commit author_
      * hg.branch - _current branch name_
      * hg.date - _time of current commit_ 
      * hg.desc - _description (message) of current commit_
      * hg.rev -  _current commit number_
      * hg.node - _current commit hash_
      * hg.bookmarks - _bookmarks attached to current commit_
      * hg.tags - _tags attached to current commit_
      * hg.tag - _current commit first tag - equal to hg.tags[0]_
      * hg.tags[i] - _current commit tag by number i_
      * hg.tags.previous - _previous closest tags attached to parent commit_
      * hg.tag.previous - _previous closest first tag attached to parent commit - equal to hg.tags.previous[0]_
      * hg.tags.previous[i] - _previous closest tag by number i attached to parent commit_
      * hg.commit.number - _commit number in current branch_

3. Example project

    https://github.com/volodya-lombrozo/hg-revision-plugin-example
