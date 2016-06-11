package com.morcinek.android.codegenerator.codegeneration.providers.resources;

import java.util.Set;

import com.google.common.collect.Sets;
import com.morcinek.android.codegenerator.extractor.model.Resource;

/**
 * @author grass
 */
public class CreatorProvider extends AbstractResourceProvider {

    public CreatorProvider(Resource resource) {
        super(resource);
    }

    @Override
    public Set<String> provideInterface() {
        return null;
    }

    @Override
    public Set<String> provideAssignment() {
        return Sets.newHashSet("Creator");
    }

    @Override
    public Set<String> provideField() {
        return Sets.newHashSet("");
    }

    @Override
    public Set<String> provideMethod() {
        return null;
    }
}
