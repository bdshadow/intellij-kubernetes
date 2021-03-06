/*******************************************************************************
 * Copyright (c) 2019 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v2.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.jboss.tools.intellij.kubernetes.model.mocks

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.fabric8.kubernetes.api.model.HasMetadata
import io.fabric8.kubernetes.client.NamespacedKubernetesClient
import org.jboss.tools.intellij.kubernetes.model.ICluster
import org.jboss.tools.intellij.kubernetes.model.IResourceChangeObservable
import org.jboss.tools.intellij.kubernetes.model.IResourceKindProvider
import org.jboss.tools.intellij.kubernetes.model.NamespaceProvider

object Mocks {

    fun clusterFactory(cluster: ICluster): (IResourceChangeObservable) -> ICluster {
        return mock() {
            doReturn(cluster)
                .whenever(mock).invoke(any())
        }
    }

    fun cluster(client: NamespacedKubernetesClient, provider: NamespaceProvider): ICluster {
        return mock() {
            doNothing()
                .whenever(mock).watch()
            doReturn(client)
                .whenever(mock).client
            doReturn(provider)
                .whenever(mock).getNamespaceProvider(any<HasMetadata>())
            doReturn(provider)
                .whenever(mock).getNamespaceProvider(any<String>())
        }
    }

    fun namespaceProvider(): NamespaceProvider{
        return mock()
    }

    fun <T: HasMetadata> resourceKindProvider(kind: Class<T>): IResourceKindProvider<T> {
        return mock() {
            doReturn(kind)
                .whenever(mock).kind
        }
    }

}