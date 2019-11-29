package org.jboss.tools.intellij.kubernetes.model

import io.fabric8.kubernetes.api.model.Namespace
import io.fabric8.kubernetes.client.NamespacedKubernetesClient

class NamespaceProvider(private val client: NamespacedKubernetesClient, val namespace: Namespace) {

    private val children: List<ResourceKindProvider> = mutableListOf(
        PodsProvider(client, namespace))

    fun <T> getChildren(kind: Class<T>): List<T> {
        val provider: ResourceKindProvider? = children.find { provider -> kind == provider.kind }
        if (provider == null) {
            return emptyList()
        }
        return provider.resources as List<T>
    }
}